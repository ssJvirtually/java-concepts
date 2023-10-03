package com.java.collections;

import java.util.HashMap;
import java.util.Map;

public class HashMapImplementation {


    public static void main(String[] args) {


        Map map = new HashMap();
        Map map2 = new HashMap();

        map.put(1,"shashi");
        map.put(2,"komal");
        map.put(3,"geeta");
        map.put(2,"Komal");

        map2.put(1,"shashi");
        map2.put(2,"komal");
        map2.put(3,"geeta");
        map2.put(2,"Komal");

        System.out.println(map.hashCode());
        System.out.println(map2.hashCode());
        System.out.println(map);

    }
}
