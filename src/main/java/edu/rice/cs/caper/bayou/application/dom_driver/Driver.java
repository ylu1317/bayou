/*
Copyright 2017 Rice University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package edu.rice.cs.caper.bayou.application.dom_driver;

import edu.rice.cs.caper.bayou.core.dom_driver.Options;
import edu.rice.cs.caper.bayou.core.dom_driver.Visitor;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Driver {

    Options options;

    public Driver(String args[]) throws ParseException, IOException {
        this.options = new Options(args);
    }

    private CompilationUnit createCompilationUnit(String[] class_paths) throws IOException {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        File input = new File(options.cmdLine.getOptionValue("input-file"));

        /*
        String[] class_paths = new String[] {
            "/Users/yanxin/Documents/work/rice/bayou/benchmark/jars/jsoup-1.11.2.jar",
            "/Users/yanxin/Documents/work/rice/bayou/benchmark/jars/htmlcleaner-2.21.jar"
        };
        */
        parser.setSource(FileUtils.readFileToString(input, "utf-8").toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setUnitName("Program.java");
        // parser.setEnvironment(new String[] { classpath != null? classpath : "" }, new String[] { "" }, new String[] { "UTF-8" }, true);
        parser.setEnvironment(class_paths, new String[] {""}, new String[] { "UTF-8" }, true);
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);

        return (CompilationUnit) parser.createAST(null);
    }

    public void execute(String[] classpaths) throws IOException {
        CompilationUnit cu = createCompilationUnit(classpaths);
        Visitor visitor = new Visitor(cu, options);
        cu.accept(visitor);
        String json = visitor.buildJson();

        if(json == null)
            return;

        PrintWriter output;
        if (options.cmdLine.hasOption("output-file"))
            output = new PrintWriter(options.cmdLine.getOptionValue("output-file"));
        else
            output = new PrintWriter(System.out);

        output.write(json);
        output.flush();
        output.close();
    }

	public static void main(String args[]) {
        try {
            // multiple class paths can be separated by semi-colon
            String classpaths = System.getenv("CLASSPATH");
            String[] paths = classpaths.split(";");
            new Driver(args).execute(paths);
        } catch (ParseException | IOException e) {
            System.out.println("This is a new version.");
            System.out.println("Unexpected exception: " + e.getMessage());
        }
	}
}
