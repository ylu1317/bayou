package edu.rice.pliny.apitrans.examples;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.type.VoidType;
import spoon.reflect.code.CtFieldRead;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.CodeFactory;
import spoon.reflect.factory.CoreFactory;
import spoon.reflect.factory.Factory;
import spoon.reflect.factory.FactoryImpl;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.DefaultCoreFactory;
import spoon.support.StandardEnvironment;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JParserTest {

    @Test
    public void hello() {
        Factory factory = new FactoryImpl(new DefaultCoreFactory(), new StandardEnvironment());
        CoreFactory core = factory.Core();
        CodeFactory code = factory.Code();

        CtFieldRead target = core.createFieldRead();
        CtTypeReference system_type = code.createCtTypeReference(System.class);
        CtTypeAccess type_access = code.createTypeAccess(system_type);
        target.setTarget(type_access);
        target.setVariable(core.createFieldReference().setSimpleName("out"));

        CtExecutableReference print_method = core.createExecutableReference();
        CtTypeReference pstream_type = code.createCtTypeReference(PrintStream.class);
        print_method.setType(pstream_type);
        print_method.setSimpleName("println");

        CtInvocation call = factory.Code().createInvocation(target,print_method);
        assertEquals("java.lang.System.out.println()", call.toString());
    }

    @Test
    public void hello2() {
        String sys_str = "System";
        String out_str = "out";
        String print_str = "println";
        NameExpr system_name = new NameExpr(sys_str);
        FieldAccessExpr out_field = new FieldAccessExpr(system_name, out_str);
        NodeList args = new NodeList();
        MethodCallExpr call = new MethodCallExpr(out_field, print_str, args);
        ExpressionStmt stmt = new ExpressionStmt(call);
        assertEquals("System.out.println();", stmt.toString());
    }
}
