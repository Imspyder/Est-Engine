package com.practice.designpattern;

public class MainSingleton {

    public static void main(String[] args) {

        EagerSingleton es1=EagerSingleton.getInstance();
        EagerSingleton es2=EagerSingleton.getInstance();
        EagerSingleton es3=EagerSingleton.getInstance();

        System.out.println(es1+"\t"+es2+"\t"+es3);
        System.out.println(es1.toString()+"\t"+es2.toString()+"\t"+es3.toString());
        System.out.println(es1.hashCode()+"\t"+es2.hashCode()+"\t"+es3.hashCode());


        LazySingleton ls1=LazySingleton.getInstance();
        LazySingleton ls2=LazySingleton.getInstance();
        LazySingleton ls3=LazySingleton.getInstance();

        System.out.println(ls1+"\t"+ls2+"\t"+ls3);
        System.out.println(ls1.toString()+"\t"+ls2.toString()+"\t"+ls3.toString());
        System.out.println(ls1.hashCode()+"\t"+ls2.hashCode()+"\t"+ls3.hashCode());

        StaticSingleton ss1=StaticSingleton.getInstance();
        StaticSingleton ss2=StaticSingleton.getInstance();
        StaticSingleton ss3=StaticSingleton.getInstance();

        System.out.println(ss1+"\t"+ss2+"\t"+ss3);
        System.out.println(ss1.toString()+"\t"+ss2.toString()+"\t"+ss3.toString());
        System.out.println(ss1.hashCode()+"\t"+ss2.hashCode()+"\t"+ss3.hashCode());



    }
}
