package com.java.examples.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile {

    public static void main(String[] args) {
    read();
    }

    public static void read() {
        try (FileReader fileReader = new FileReader(new File("C:\\Users\\jskr4\\Downloads\\System_design_Notes.txt"))) {
            while (fileReader.read() != -1){
                System.out.println((char) fileReader.read());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List<String> strings = Files.readAllLines(Path.of("C:\\Users\\jskr4\\Downloads\\System_design_Notes.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
