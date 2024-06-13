package com.practice.designpattern;

public class EagerSingleton {

    private static EagerSingleton as=new EagerSingleton();

    private EagerSingleton(){}

    public static EagerSingleton getInstance(){
        return as;
    }


}
