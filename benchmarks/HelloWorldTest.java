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
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.VoidType;
import org.junit.Test;
import spoon.reflect.code.*;
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

public class HelloWorldTest {

    @Test
    public void hello() {
        Factory factory = new FactoryImpl(new DefaultCoreFactory(), new StandardEnvironment());
        // SpoonAPI spoon = new Launcher();
        // spoon.addInputResource("/Users/yanxin/Documents/work/rice/code-completion/code_completion/src/main/java/edu/rice/pliny/mcts/Foo.java");
        // spoon.run();

        CoreFactory core = factory.Core();
        CodeFactory code = factory.Code();

        CtClass hello_class = core.createClass();
        hello_class.setSimpleName("HelloWorld");

        CtMethod hello_method = core.createMethod();
        hello_method.setSimpleName("main");
        CtTypeReference method_ret_type = code.createCtTypeReference(Void.class);
        hello_method.setType(method_ret_type);

        CtFieldRead target = core.createFieldRead();
        CtTypeReference system_type = code.createCtTypeReference(System.class);
        CtTypeAccess type_access = code.createTypeAccess(system_type);
        target.setTarget(type_access);
        target.setVariable(core.createFieldReference().setSimpleName("out"));

        CtExecutableReference print_method = core.createExecutableReference();
        CtTypeReference pstream_type = code.createCtTypeReference(PrintStream.class);
        print_method.setType(pstream_type);
        print_method.setSimpleName("println");

        CtExpression hello_str = core.createLiteral().setValue("Hello world.");

        CtInvocation call = factory.Code().createInvocation(target,print_method,hello_str);
        CtBlock body_block = code.createCtBlock(call);

        hello_method.setBody(body_block);
        hello_class.addMethod(hello_method);
        String str = hello_class.toString();
        System.out.println(str);
    }

    @Test
    public void hello2() {
        ClassOrInterfaceDeclaration hello_class = new ClassOrInterfaceDeclaration();
        hello_class.setName("HelloWorld");
        MethodDeclaration hello_method = new MethodDeclaration();
        hello_method.setName("main");
        hello_method.setType(new VoidType());
        hello_method.setStatic(true);
        hello_method.setModifier(Modifier.PUBLIC, true);

        MethodCallExpr call = new MethodCallExpr();
        FieldAccessExpr out_field = new FieldAccessExpr();
        out_field.setName("out");
        out_field.setScope(new NameExpr("System"));
        call.setScope(out_field);
        call.setName("println");

        NodeList args = new NodeList();
        args.add(new StringLiteralExpr("Hello World."));
        call.setArguments(args);

        ExpressionStmt stmt = new ExpressionStmt(call);
        NodeList stmt_list = new NodeList();
        stmt_list.add(stmt);

        BlockStmt body_block = new BlockStmt(stmt_list);
        hello_method.setBody(body_block);
        hello_class.getMembers().add(hello_method);

        System.out.println(hello_class);
    }
}
