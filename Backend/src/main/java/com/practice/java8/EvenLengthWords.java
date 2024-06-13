package com.practice.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EvenLengthWords {

    public static void main(String[] args) {

        String str = "Print string which have even length";
        String[] strArray = str.split(" ");

        Arrays.stream(strArray).parallel().filter(a->a.length()%2==0).forEach(System.out::println);

    }
}
