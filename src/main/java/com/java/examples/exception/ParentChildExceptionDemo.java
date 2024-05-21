package com.java.examples.exception;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Parent{

    public void readFile() throws IOException {
        File file = new File("non_existent_file.txt");

        FileReader fileReader = new FileReader(file);
    }
}

class Child extends Parent{

    public void readFile() throws IOException {

        String string = Files.readString(Path.of(""));

    }
}
public class ParentChildExceptionDemo {

    public static void main(String[] args) throws IOException {
       new Child().readFile();
    }
}
