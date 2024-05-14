package com.java.examples.leetcode;

public class IsSubSequence {

    public static void main(String[] args) {
        String s ="aaaaaa";
        String t = "bbaaaa";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.indexOf(s,5);
        System.out.println(isSubsequence(s,t));
    }

    public static  boolean isSubsequence(String s, String t) {

        char[] chars = s.toCharArray();

        StringBuilder stringBuilder = new StringBuilder(t);
        int currIndex = 0;
        int count = 0;

        while(count<chars.length){


            currIndex = stringBuilder.indexOf(String.valueOf(chars[count]),currIndex);

            if(currIndex == -1){
                return false;
            }
            currIndex++;

            count++;
        }
        return true;

    }
}
