package com.java.examples.temp;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class YamlModifier {
    private static Map<String, String> replacementValues = new HashMap<>();

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

            // Search for env in the YAML structure
            findEnvValues(valuesMap);

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

    @SuppressWarnings("unchecked")
    private static void findEnvValues(Object obj) {
        if (obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) obj;

            // Check if current map has 'env' key
            if (map.containsKey("env")) {
                Object envValue = map.get("env");
                if (envValue instanceof List) {
                    processEnvList((List<Map<String, Object>>) envValue);
                }
            }

            // Recursively search in all map values
            for (Object value : map.values()) {
                findEnvValues(value);
            }
        } else if (obj instanceof List) {
            // Recursively search in all list elements
            List<?> list = (List<?>) obj;
            for (Object item : list) {
                findEnvValues(item);
            }
        }
    }

    private static void processEnvList(List<Map<String, Object>> envList) {
        for (Map<String, Object> envEntry : envList) {
            if (envEntry.containsKey("name") && envEntry.containsKey("value")) {
                String name = envEntry.get("name").toString();
                String value = envEntry.get("value").toString();
                replacementValues.put(name, value);
            }
        }
    }
}