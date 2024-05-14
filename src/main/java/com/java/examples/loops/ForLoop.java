package com.java.examples.loops;

import java.util.HashMap;
import java.util.Map;

public class ForLoop {


    public static void main(String[] args) {


        String s = "IC";

        Map<Character,Integer> romanValues = new HashMap();

        romanValues.put('I',1);
        romanValues.put('V',5);
        romanValues.put('X',10);
        romanValues.put('L',50);
        romanValues.put('C',100);
        romanValues.put('D',500);
        romanValues.put('M',1000);
        int sum = 0;
        for(int i=0;i<s.length();){
            if(i<s.length()-1 && romanValues.get(s.charAt(i)) < romanValues.get(s.charAt(i+1))){


                sum += romanValues.get(s.charAt(i+1)) - romanValues.get(s.charAt(i));
                i = i+2;
            }
            else {
                sum += romanValues.get(s.charAt(i));
                i++;
            }
        }

        System.out.println(sum);
    }
}
