package com.jsontoxml.converter.filegeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassGenerator {
    public static void main(String[] args) {
        String packageName = "com.jsontoxml.converter.filegeneration";
        String className = "MyClassw";
        String classContent = "public interface " + className + " {\n" +
                "    //fields, methods, and constructors here\n" +
                "}\n";

        // Create the package directory
       /* String packageDir = packageName.replace(".", File.separator);
        new File(packageDir).mkdirs();
*/
        String packageDir = "D:\\converter\\converter\\src\\main\\java\\com\\jsontoxml\\converter\\filegeneration";
        // Create the class file
        File classFile = new File(packageDir + "\\" + className + ".java");
        try (FileWriter fileWriter = new FileWriter(classFile)) {
            fileWriter.write("package " + packageName + ";\n\n");
            fileWriter.write(classContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
