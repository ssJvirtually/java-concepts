package com.java.examples.serialization;

import java.io.Serializable;

public class Person implements Serializable {

     int id;
     String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}