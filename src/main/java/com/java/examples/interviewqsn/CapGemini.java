package com.java.examples.interviewqsn;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

//how you implemented microservices , what architecture you have used
//how do you save inherited classes in database as entity
//how map or list gets stored in database using jpa
public class CapGemini {


    public static void main(String[] args) {
        int[] arr = {1,2,3,2,4,6,3,2};
        int n = arr.length;


        //bubble sort
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[j]<=arr[i]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }



        //insertion sort
        for(int i=1;i<n;i++){
            int k =i;

            while(k>0 && arr[k-1] > arr[k]){
                int temp = arr[k-1];
                arr[k-1] = arr[k];
                arr[k] = temp;
                k--;
            }
        }

        //selection sort
        for(int i=0;i<n;i++){
            int minIndex = i;

            int j = i+1;
            while(j < n){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
                j++;
            }

            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }






        for(int i=1;i<n;i++){
            if(arr[i-1] == arr[i]){
                int j = i+1;
                while(j < n){
                    if(arr[j] > arr[i]){
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        break;
                    }
                    j++;
                }
            }

            if( arr[i] < arr[i-1]){
                int j = i+1;
                while(j < n){

                    if(arr[j] > arr[i-1]){
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j]= temp;
                        break;
                    }
                    j++;
                }

            }

        }

        System.out.println(Arrays.toString(arr));

        String[] isoCountries = Locale.getISOCountries();

        for(String isoCountry : isoCountries)
        {
            System.out.println(isoCountry);
        }
        Locale[] availableLocales = Locale.getAvailableLocales();

        for(Locale locale : availableLocales)
        {
            System.out.println(locale.getDisplayCountry());
        }   
        Locale locale = new Locale("en", "IN");
        String displayCountry = locale.getDisplayCountry();

        String displayCountry1 = locale.getDisplayName();
//        Set<String> in = Locale.getISOCountries(Locale.IsoCountryCode.valueOf("IN"));
        Locale.IsoCountryCode isoCountryCode = Locale.IsoCountryCode.valueOf("IN");

    }
}
