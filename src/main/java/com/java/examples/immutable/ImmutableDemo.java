package com.java.examples.immutable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class ImmutableClass{

    private final int id;

    private final String name;


    private final List<Integer> contacts;

    private final Date date;

    public ImmutableClass(int id, String name, List<Integer> contacts, Date date) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public List<Integer> getContacts() {
//        return contacts;
//    }

    //instead of return the list do deep copy of the original list and return
    public List<Integer> getContacts() {
        List<Integer> copy = new ArrayList<>(contacts);
        return copy;
    }


//    public Date getDate() {
//        return date;
//    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}



public class ImmutableDemo {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        list.addAll(List.of(1,2,3,4,5));

        ImmutableClass immutableClass = new ImmutableClass(1,"sj",list,new Date());


        //print list
        System.out.println(immutableClass.getContacts());
        System.out.println(immutableClass.getDate());

        //add new data to the list
        immutableClass.getContacts().add(22);
        immutableClass.getDate().setDate(22);

        //print list
        System.out.println(immutableClass.getContacts());
        System.out.println(immutableClass.getDate());

        //before doing deep copy
//        [1, 2, 3, 4, 5]
//        Fri Jun 07 10:50:39 IST 2024
        //modified data
//        [1, 2, 3, 4, 5, 22]
//        Sat Jun 22 10:50:39 IST 2024

         //after doing deep copy
//        [1, 2, 3, 4, 5]
//        Fri Jun 07 00:00:00 IST 2024
//        [1, 2, 3, 4, 5]
//        Fri Jun 07 00:00:00 IST 2024








    }

}
