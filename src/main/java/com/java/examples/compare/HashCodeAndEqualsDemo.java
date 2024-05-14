package com.java.examples.compare;



import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashCodeAndEqualsDemo {

    public static void main(String[] args) {
        Map<DataKeyWithHashAndEqual, Integer> hm = getAllData();

        DataKeyWithHashAndEqual dk = new DataKeyWithHashAndEqual();
        dk.setId(1);
        dk.setName("Pankaj");
        System.out.println(dk.hashCode());

        Integer value = hm.get(dk);

        System.out.println(value);

    }

    private static Map<DataKeyWithHashAndEqual, Integer> getAllData() {
        Map<DataKeyWithHashAndEqual, Integer> hm = new HashMap<>();

        DataKeyWithHashAndEqual dk = new DataKeyWithHashAndEqual();
        dk.setId(1);
        dk.setName("Pankaj");
        System.out.println(dk.hashCode());

        hm.put(dk, 10);

        return hm;
    }

}

class DataKey {

    private String name;
    private int id;

    // getter and setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DataKey [name=" + name + ", id=" + id + "]";
    }
}

class DataKeyWithHashAndEqual {

    private String name;
    private int id;

    // getter and setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DataKey [name=" + name + ", id=" + id + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataKeyWithHashAndEqual that = (DataKeyWithHashAndEqual) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}


