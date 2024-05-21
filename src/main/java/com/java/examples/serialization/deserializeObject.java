package com.java.examples.serialization;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class deserializeObject {


    public static void main(String[] args) {

        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("obj.bin"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


          //read one object
//        Person person;
//        try {
//            person = (Person) objectInputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(person.name);


        //read all objects
        List<Object> objects = new ArrayList<>();

            while (true) {
                try {
                    // Read object and add it to the list
                    Object object =  objectInputStream.readObject();
                    objects.add(object);
                } catch (IOException | ClassNotFoundException e) {
                    // End of file reached, break out of the loop
                    break;
                }
            }


        // Print all deserialized objects
        for(Object object : objects)
            System.out.println(object.toString());
        }

}
