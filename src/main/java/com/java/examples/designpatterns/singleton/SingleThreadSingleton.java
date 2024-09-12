package com.java.examples.designpatterns.singleton;

class SingletonInstance{

    private SingletonInstance(){

    }

    private static SingletonInstance singletonInstance = null;


    public static SingletonInstance getInstance(){
        if(singletonInstance == null){
            singletonInstance = new SingletonInstance();
        }
        return singletonInstance;
    }


}


public class SingleThreadSingleton {


    public static void main(String[] args) {

        SingletonInstance instance1 = SingletonInstance.getInstance();
        SingletonInstance instance2 = SingletonInstance.getInstance();
        System.out.println(instance1.hashCode() + " " + instance2.hashCode());
    }
}
