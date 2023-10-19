package com.jsontoxml.converter.filegeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateClass {
    public static void main(String[] args) throws IOException {
        String directory = "D:\\converter\\converter\\src\\main\\java\\com\\jsontoxml\\converter\\filegeneration";
        String className = "Example2";

        File file = new File(directory + "\\" + className + ".java");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("public class " + className + "{\n}");
            writer.close();
            System.out.println("Class generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
