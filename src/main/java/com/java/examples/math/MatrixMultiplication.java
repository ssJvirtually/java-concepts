package com.java.examples.math;

import java.util.Arrays;

public class MatrixMultiplication {

    public static void main(String[] args) {
        int[][] a= {{1,2,3},{4,5,7}};
        int[][] b = {{1,2},{3,4},{5,6}};

        int[][] ints = multiplyMatrx(a, b);

        System.out.println(Arrays.deepToString(ints));


    }

    public static int[][] multiplyMatrx(int[][] a,int[][]b){

        if(a.length != b[0].length){
            throw new IllegalArgumentException("Matrix multiplication not possible");
        }

        int[][] resultMatrix = new int[a.length][b[0].length];

        //iterate row
        for(int i=0;i<a.length;i++){

            //iterate column
            for(int j=0;j<b[0].length;j++){

                // iterate inner matrix
                for(int k=0;k<b.length;k++){
                   resultMatrix[i][j] += a[i][k]*b[k][j];
                }
            }
        }

        return resultMatrix;

    }









}
