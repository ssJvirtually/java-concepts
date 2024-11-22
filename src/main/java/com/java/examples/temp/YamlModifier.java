package com.java.examples.temp;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class YamlModifier {
    public static void main(String[] args) {
        try {
            // Read the placeholder YAML file
            String templateContent = new String(Files.readAllBytes(Paths.get("placeholder.yaml")));

            // Read the values YAML file
            Yaml yaml = new Yaml();
            Map<String, Object> valuesMap;
            try (InputStream inputStream = new FileInputStream("values.yaml")) {
                valuesMap = yaml.load(inputStream);
            }

            // Create a map of name-value pairs from the nested structure
            Map<String, String> replacementValues = new HashMap<>();
            List<Map<String, Object>> envList = ((List<Map<String, Object>>) valuesMap.get("env"));
            for (Map<String, Object> envEntry : envList) {
                String name = (String) envEntry.get("name");
                String value = (String) envEntry.get("value");
                replacementValues.put(name, value);
            }

            // Replace placeholders (format: ${name})
            String modifiedContent = templateContent;
            for (Map.Entry<String, String> entry : replacementValues.entrySet()) {
                String placeholder = "${" + entry.getKey() + "}";
                String value = entry.getValue();
                modifiedContent = modifiedContent.replace(placeholder, value);
            }

            // Write the modified content to output.yaml
            Files.write(Paths.get("output.yaml"), modifiedContent.getBytes());

            System.out.println("YAML file has been successfully modified!");

        } catch (IOException e) {
            System.err.println("Error processing YAML files: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error processing YAML structure: " + e.getMessage());
            e.printStackTrace();
        }
    }
}