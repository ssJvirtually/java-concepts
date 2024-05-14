package com.java.examples.records;

public record EmployeeRecord(int id, String firstName) {


    //canonical constructor
     public EmployeeRecord(int id, String firstName){
        if(id <3 ){
        throw  new IllegalArgumentException("id cannot be less");
        }

        this.id = id;

        this.firstName = firstName;

    }


//    public EmployeeRecord {
//
//        if(id <3 ){
//            throw  new IllegalArgumentException("sdfsdf");
//        }
//    }
}

