package com.jsontoxml.converter.utils;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class ProcessHelper {
    private OutputStreamWriter output;

    public ProcessHelper() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("java ProcessHelper");
        output = new OutputStreamWriter(process.getOutputStream());
    }

    public void exec(String command) throws IOException {
        output.write(command, 0, command.length());
    }
}