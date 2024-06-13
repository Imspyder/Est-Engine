package com.practice.java8;

import java.util.Arrays;
import java.util.List;

public class Java8Practice {

    public static List<Student> getAllStudents() {
        return Arrays.asList(
                new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
                new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
                new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
                new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
                new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
                new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
                new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
                new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
                new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
                new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

    }

    public static void main(String[] args) {


//        Find the list of students whose names starts from A
//        getAllStudents().parallelStream().filter(s->s.getFirstName().startsWith("A")).forEach(System.out::println);

//        Group the student by department name
//        System.out.print(getAllStudents().parallelStream().collect(Collectors.groupingBy(Student::getDepartmantName)));;

//        find the total count of students
//        System.out.println("Total number of students = " + getAllStudents().stream().count());

//        Find the max age of Student
//        System.out.println(getAllStudents().parallelStream().max(Comparator.comparing(Student::getAge)));

//        Find all department names
//        getAllStudents().parallelStream().map(Student::getDepartmantName).distinct().forEach(System.out::println);

//        FInd the count of students in each department
//        System.out.println(getAllStudents().parallelStream().collect(Collectors.groupingBy(Student::getDepartmantName,Collectors.counting())));

//        Find the list of students whose age is less than 30
//        getAllStudents().parallelStream().filter(s->s.getAge()<30).forEachOrdered(System.out::println);

//        Find the list of students whose rank is in between 50 and 100
//        getAllStudents().parallelStream().filter(s->50<s.getRank() && s.getRank()<100).forEach(System.out::println);

//        9- Find the average age of male and female students
//        System.out.println(getAllStudents().parallelStream().collect(Collectors.groupingBy(Student::getGender,Collectors.averagingInt(Student::getAge))));;

//        10- Find the department who is having maximum number of students
//        System.out.println(getAllStudents().parallelStream().max(Comparator.comparing(Student::getDepartmantName)).get().getDepartmantName());;

//        11- Find the Students who stays in Delhi and sort them by their names
//            getAllStudents().stream().filter(s->s.getCity().equalsIgnoreCase("Karnataka")).sorted(Comparator.comparing(Student::getFirstName)).forEach(System.out::println);

//        12- Find the average rank in all departments
//        System.out.println(getAllStudents().parallelStream().collect(Collectors.averagingInt(Student::getRank)));


//        13- Find the highest rank in each department
//        System.out.println(getAllStudents().parallelStream().collect(Collectors.groupingBy(Student::getDepartmantName,Collectors.minBy(Comparator.comparing(Student::getRank)))));



    }
}
