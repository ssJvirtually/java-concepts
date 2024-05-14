package com.java.examples.multithreading;

import java.io.*;
import java.net.URL;

class DownloaderTask implements Runnable {
    private String videoUrl;
    private String destinationPath;

    public DownloaderTask(String videoUrl, String destinationPath) {
        this.videoUrl = videoUrl;
        this.destinationPath = destinationPath;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(videoUrl);
            String fileName = getFileNameFromUrl(url);
            // Append the filename to the destination path
            destinationPath = destinationPath +  fileName;

            System.out.println(destinationPath);
            try (BufferedInputStream in = new BufferedInputStream(url.openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(destinationPath)) {

                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                long downloadedBytes = 0;
                long fileSize = url.openConnection().getContentLengthLong();

                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    downloadedBytes += bytesRead;

                    // Update progress bar
                    printProgressBar(downloadedBytes, fileSize);
                }

                System.out.println("\nDownload completed: " + destinationPath);
            }
        } catch (IOException e) {
            System.err.println("Error while downloading video: " + e.getMessage());
        }
    }


    private String getFileNameFromUrl(URL url) {
        String path = url.getPath();
        int slashIndex = path.lastIndexOf('/');
        if (slashIndex != -1 && slashIndex < path.length() - 1) {
            return path.substring(slashIndex + 1);
        } else {
            // If the URL doesn't have a valid path, generate a random filename
            return "downloaded_file_" + System.currentTimeMillis();
        }
    }

    private void printProgressBar(long downloadedBytes, long fileSize) {
        int progressWidth = 50;
        double progressPercentage = (double) downloadedBytes / fileSize;
        int progressBarLength = (int) (progressWidth * progressPercentage);

        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < progressWidth; i++) {
            if (i < progressBarLength) {
                progressBar.append("=");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append("] ");
        progressBar.append(String.format("%.2f", progressPercentage * 100)).append("%");

        System.out.print("\r" + progressBar);
    }
}
