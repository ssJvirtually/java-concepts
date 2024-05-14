package com.java.examples.keywords;

public class ThisKeyword {

    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(23);
        person.setName("Shashi");

        System.out.println(person.getName());

        System.out.println(person.getAge());


        System.out.println(person.toString());
    }
}

class Person implements  Cloneable{


   private  String name = "sam";

   private int age;

    Person() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
