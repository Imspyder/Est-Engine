package com.practice.designpattern;

public class FactoryShape {

    public Shape factoryMethod(String str){
        Shape s=null;
        if(str.equals("circle")){
             s=new Circle();
        }else if(str.equals("rectangle")){
             s= new Rectangle();

        }else {
             s=new Square();
        }
        return s;
    }

    public static void main(String[] args) {

        FactoryShape fs=new FactoryShape();
        fs.factoryMethod("").draw();


    }
}
