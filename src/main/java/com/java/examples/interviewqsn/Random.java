package com.java.examples.interviewqsn;

import java.util.Arrays;

public class Random {


    public static void main(String[] args) {

        int[] nums = {0,1,0,1,0,1,0,1,0,1};



        System.out.println(Arrays.toString(nums));
    }


    static void swap(int[] nums) {
        int j =nums.length-1;
        for(int i=0;i<nums.length;i++){

            if(nums[i] == 1 && j > nums.length/2){
                while(j >nums.length/2){
                    if(nums[j] == 0){
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        break;
                    }
                    j--;
                }
            }

        }
    }
}
