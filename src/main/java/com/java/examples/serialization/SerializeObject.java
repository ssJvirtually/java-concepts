package com.java.examples.serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;



public class SerializeObject {

    public static void main(String[] args) {

        Person person = new Person(1, "personnn");
        Employee employee = new Employee(1, "Employeee");

        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("obj.bin"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            objectOutputStream.writeObject(person);
            objectOutputStream.writeObject(employee);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
