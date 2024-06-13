package com.practice.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseWord {

    public static void main(String[] args) {

        String str = "This is sample String";
        String[] strArray = str.split(" ");

        for (int i = strArray.length - 1; i >= 0; i--) {
            System.out.print(strArray[i] + " ");
        }

       Stream<String> a= Arrays.stream(strArray).parallel();
        a.collect(Collectors.collectingAndThen(Collectors.toList(),arr->{Collections.reverse(arr);
        return arr.stream();})).forEach(System.out::print);
    }


}
