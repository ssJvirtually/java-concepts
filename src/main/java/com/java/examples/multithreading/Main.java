package com.java.examples.multithreading;

public class Main {
    public static void main(String[] args) {
        String videoUrl = "https://sample-videos.com/video123/mp4/480/big_buck_bunny_480p_30mb.mp4"; // Replace this with the actual video URL
        String destinationPath = ""; // Replace this with the destination path

        DownloaderTask downloaderTask = new DownloaderTask(videoUrl, destinationPath);
        DownloaderTask downloaderTask2 = new DownloaderTask("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_30mb.mp4", destinationPath);

        Thread downloadThread = new Thread(downloaderTask);
        Thread downloadThread2 = new Thread(downloaderTask2);
        downloadThread.start();
        downloadThread2.start();
//        downloaderTask.run();
//        downloaderTask2.run();
    }
}
