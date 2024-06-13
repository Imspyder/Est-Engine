package com.practice.java8;

import java.util.Arrays;

public class InsertStringIntoAnotherString {

    public static void main(String[] args) {

        String str = "TitTat";

        String strToBeInserted="for";

        String s = str.substring(0, 3) + strToBeInserted + str.substring(3);
        System.out.println("Complete String :  "+s);

    }
}
