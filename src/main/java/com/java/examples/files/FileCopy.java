package com.java.examples.files;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileCopy {
    public static void main(String[] args) {


        String sourceFolder = "E:\\2-09-2023 Venkat Marriage\\Engagement\\100MSDCF";
        String destFolder = "D:\\Girl_selected_pics\\Engagement";
        String textFile = "C:\\Users\\HP\\Downloads\\fileNames.txt";

        try {
            List<String> filesToCopy = Files.readAllLines(Paths.get(textFile));
            int totalFiles = filesToCopy.size();
            int copiedFiles = 0;

            System.out.println("Copying files...");
            for (String fileName : filesToCopy) {
                File sourceFile = new File(sourceFolder + File.separator + fileName);
                File destFile = new File(destFolder + File.separator + fileName);
                if (sourceFile.exists() && sourceFile.isFile()) {
                    Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    copiedFiles++;
                    System.out.print("\r" + copiedFiles + "/" + totalFiles);
                } else {
                    System.out.println("File not found in source folder: " + fileName);
                }
            }

            System.out.println("Copy operation completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
