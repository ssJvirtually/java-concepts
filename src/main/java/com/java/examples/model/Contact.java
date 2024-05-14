package com.java.examples.model;

public class Contact {

    String type;

    Long number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "type='" + type + '\'' +
                ", number=" + number +
                '}';
    }
}
