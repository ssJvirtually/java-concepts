package com.java.examples.reflections;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.*;

public class DynamicClassGenerator {
    public static void main(String[] args) throws Exception {
        String className = "Person";
        String fieldName = "name";
        String fieldValue = "John Doe";

        Class<?> generatedClass = generateClass(className, fieldName);
        Constructor<?> constructor = generatedClass.getConstructor(String.class);
        Object instance = constructor.newInstance(fieldValue);

        System.out.println(instance);
    }

    private static Class<?> generateClass(String className, String fieldName) throws Exception {
        String sourceCode = "public class " + className + " { " +
                "private String " + fieldName + "; " +
                "public " + className + "(String " + fieldName + ") { " +
                "this." + fieldName + " = " + fieldName + "; }" +
                "public String get" + capitalize(fieldName) + "() { return " + fieldName + "; } " +
                "public void set" + capitalize(fieldName) + "(String " + fieldName + ") { this." + fieldName + " = " + fieldName + "; }" +
                "}";

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File sourceFile = new File(className + ".java");
        JavaFileObject source = new DynamicJavaSourceCodeObject(className, sourceCode);
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(new File("./src/main/java/com/java/reflections")));

        List<JavaFileObject> sourceFiles = new ArrayList<>();
        sourceFiles.add(source);
        Iterable<? extends JavaFileObject> compilationUnits = sourceFiles;
        List<String> options = Arrays.asList("-classpath", System.getProperty("java.class.path"));
        compiler.getTask(null, fileManager, null, options, null, compilationUnits).call();
        fileManager.close();

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("./src/main/java/com/java/reflections").toURI().toURL() });
        return classLoader.loadClass(className);
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}

class DynamicJavaSourceCodeObject extends SimpleJavaFileObject {
    private final String code;

    public DynamicJavaSourceCodeObject(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
