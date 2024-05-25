package com.java.examples.keywords;


import java.io.*;

class Employee implements Serializable {
     int id;
    String name;

    transient String password;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


public class TransientKeyWord {


    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "Employee";
        employee.password = "password";

        System.out.println(employee.toString());

        try {
            new ObjectOutputStream(new FileOutputStream("employee.bin")).writeObject(employee);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            Employee emp = (Employee) new ObjectInputStream(new FileInputStream("employee.bin")).readObject();
            System.out.println(emp.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
