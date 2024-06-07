package com.java.examples.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadManager extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ExecutorService executor;

    public DownloadManager() {
        setTitle("Download Manager");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the table model
        String[] columns = {"Sno", "File Name", "Download Progress", "Down Speed", "Up Speed", "ETA"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Center add button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this::showAddDialog);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.SOUTH);

        add(panel);

        // Initialize the executor for multithreading
        executor = Executors.newFixedThreadPool(4);
    }

    private void showAddDialog(ActionEvent e) {
        String url = JOptionPane.showInputDialog(this, "Enter download link:");
        if (url != null && !url.isEmpty()) {
            addDownload(url);
        }
    }

    private void addDownload(String url) {
        int row = tableModel.getRowCount() + 1;
        tableModel.addRow(new Object[]{row, getFileName(url), "0%", "0 B/s", "0 B/s", "Calculating..."});
        executor.submit(new DownloadTask(url, tableModel, row - 1));
    }

    private String getFileName(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DownloadManager manager = new DownloadManager();
            manager.setVisible(true);
        });
    }

}

class DownloadTask implements Runnable {
    private String url;
    private DefaultTableModel tableModel;
    private int row;

    public DownloadTask(String url, DefaultTableModel tableModel, int row) {
        this.url = url;
        this.tableModel = tableModel;
        this.row = row;
    }

    @Override
    public void run() {
        try {
            URL urlObj = new URL(url);
            long fileSize = urlObj.openConnection().getContentLengthLong();

            try (BufferedInputStream in = new BufferedInputStream(urlObj.openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(getFileName(url))) {

                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                final long[] totalBytesRead = {0};
                final long startTime = System.currentTimeMillis();

                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    totalBytesRead[0] += bytesRead;
                    long currentTime = System.currentTimeMillis();
                    double elapsedTime = (currentTime - startTime) / 1000.0; // in seconds
                    final double downloadSpeed = totalBytesRead[0] / elapsedTime; // bytes per second

                    SwingUtilities.invokeLater(() -> {
                        double progress = (totalBytesRead[0] / (double) fileSize) * 100;
                        tableModel.setValueAt(String.format("%.2f%%", progress), row, 2);
                        tableModel.setValueAt(String.format("%.2f B/s", downloadSpeed), row, 3);
                        tableModel.setValueAt("0 B/s", row, 4); // Up speed not applicable
                        double eta = (fileSize - totalBytesRead[0]) / downloadSpeed;
                        tableModel.setValueAt(String.format("%.2f s", eta), row, 5);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> tableModel.setValueAt("Failed", row, 2));
        }
    }

    private String getFileName(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }
}