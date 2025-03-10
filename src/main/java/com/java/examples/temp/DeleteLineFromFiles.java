import java.io.*;

public class DeleteLineFromFiles {
    public static void main(String[] args) {
        String folderPath = "path/to/folder"; // Change this to your folder path
        String keyword = "JAVA_OPTS";

        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("Invalid folder path");
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null) {
            System.out.println("No files found in the folder");
            return;
        }

        for (File inputFile : files) {
            File tempFile = new File(inputFile.getParent(), "tempFile.txt");
            
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // If the line contains the keyword, skip writing it to the new file
                    if (!currentLine.contains(keyword)) {
                        writer.write(currentLine);
                        writer.newLine();
                    }
                }
            
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            // Replace the original file with the updated file
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                System.out.println("Could not delete original file: " + inputFile.getName());
            }
        }
    }
}
