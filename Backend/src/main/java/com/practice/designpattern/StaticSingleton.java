package com.practice.designpattern;

public class StaticSingleton {

    private static StaticSingleton ss;
    private StaticSingleton(){}

    static{
        try {
            ss = new StaticSingleton();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static StaticSingleton getInstance(){
        return ss;
    }

}
