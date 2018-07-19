package edu.rice.pliny.apitrans.examples;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * counts the number of classes in a file
 */
public class JParserTest3 {

    String prog = "code_completion\\src\\test\\resources\\apitrans\\jparser3\\foo.java";

    //read file content into a string
    public static String read_file(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[10];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            // System.out.println(numRead);
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }

        reader.close();

        return fileData.toString();
    }

    static public class EVisitor extends ASTVisitor {
        public int count = 0;
        @Override
        public boolean visit(org.eclipse.jdt.core.dom.TypeDeclaration node) {
            count += 1;
            return true;
        }
    }

    static public class JPVisitor extends VoidVisitorAdapter {
        public int count = 0;

        @Override
        public void visit(ClassOrInterfaceDeclaration node, Object args) {
            count += 1;
        }
    }

    @Test
    public void hello() throws IOException {
        String src = read_file(prog);
        EVisitor ev = new EVisitor();

        ASTParser parser = ASTParser.newParser(AST.JLS3);
        Map options = new HashMap();
        parser.setCompilerOptions(options);
        parser.setSource(src.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        org.eclipse.jdt.core.dom.CompilationUnit cu = (org.eclipse.jdt.core.dom.CompilationUnit) parser.createAST(null);
        cu.accept(ev);
        int result = ev.count;
        assertEquals(3, result);
    }

    @Test
    public void hello2() throws FileNotFoundException {
        File f = new File(prog);
        JPVisitor jpv = new JPVisitor();
        CompilationUnit cu = JavaParser.parse(f);
        cu.accept(jpv, null);
        int result = jpv.count;
        assertEquals(3, result);
    }
}
