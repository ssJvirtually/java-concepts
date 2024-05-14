package com.java.examples.lambdas;

interface test{

    String revString(String s);
}


public class Main {

    public static void main(String[] args) {


        test rs = s -> {
            return new StringBuilder(s).reverse().toString();
        };

        System.out.println(rs.revString("shashi"));



//        Addition a = new Addition();
//        int addAns = a.calculate(3, 4);
//        System.out.println(addAns);

//        Calculator calculator = new Calculator() {
//            @Override
//            public int calculate(int a, int b) {
//                return a+b;
//            }
//        };



        Calculator addition = (a, b) -> a+b  ;
        Calculator substraction = (a, b) -> a-b  ;

        System.out.println(addition.calculate(4,5));


        Substraction s = new Substraction();
//
//        int subAns = s.calculate(5, 3);
//        System.out.println(subAns);





    }


}
