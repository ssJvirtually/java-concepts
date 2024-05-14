package com.java.examples.serializationanddeserialization;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SerilazationDemo implements  Serializable{


    int id;
    transient String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerilazationDemo that = (SerilazationDemo) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static void main(String[] args) {


        SerilazationDemo serilazationDemo = new SerilazationDemo();

        serilazationDemo.setId(111);

        serilazationDemo.setName("shashi");

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("object.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.write(serilazationDemo.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            outputStream.writeObject(serilazationDemo);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Override
    public String toString() {
        return "SerilazationDemo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
