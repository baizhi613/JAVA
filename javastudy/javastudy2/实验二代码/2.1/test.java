package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author lys612411
 */
public class test {
    public static void main(String[] args){
        List<Student> students =new ArrayList<>();
        students.add(new Student("Alice", 20,"女"));
        students.add(new Student("Bob", 22,"男"));
        students.add(new Student("Charlie", 19,"女"));
        students.add(new Student("David", 21,"男"));
        students.add(new Student("Eva", 20,"女"));
        students.add(new Student("Frank", 23,"男"));
        students.add(new Student("Grace", 18,"女"));
        students.add(new Student("Henry", 24,"男"));
        students.add(new Student("Ivy", 20,"男"));
        students.add(new Student("Jack", 25,"男"));

        Collections.sort(students, Comparator.comparingInt(Student::getNumber));

        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();

        students.add(new Student("张三",30,"男"));

        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();

        students.add(3,new Student("李四",10,"女"));

        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();

        students.remove(2);

        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();

        if(students.contains(students.get(1))){
            System.out.println("找到了");
        }
        else{
            System.out.println("没找到");
        }
    }
}
