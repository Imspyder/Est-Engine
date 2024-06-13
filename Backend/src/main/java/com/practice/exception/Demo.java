package com.practice.exception;

public class Demo {

    public static void main(String[] args) throws Exception {

        ParentOne p=new ParentOne();
        p.m1();
        ChildOne c=new ChildOne();
        c.m1();
        ParentOne pc=new ChildOne();
        pc.m1();


    }


}
