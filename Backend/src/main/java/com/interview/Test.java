package com.interview;

import java.util.*;

public class Test {


    public static void main(String[] args) {
        List<Integer> myList = Arrays.asList(29,15,81,22,36,58,54,57,85);

        List<Integer> l=new ArrayList<>(10);

        l.add(5,200);
        System.out.println(l);

        Set<Integer> s=new HashSet<>();


        myList.stream().filter(e-> !s.add(e) ).forEach(System.out::println);

//        myList.stream().filter(m-> m.toString().startsWith("1")).forEach(System.out::println);

//        myList.stream().map(l->l%10).sorted().forEach(System.out::println);

    }





}
