package org.example;

import java.io.*;
import java.util.Scanner;

/**
 * @author lys612411
 */
public class StudentSaver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 从键盘输入学生信息
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();

        System.out.println("请输入学生年龄：");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("请输入学生身份证号：");
        String idCardNumber = scanner.nextLine();

        Student student = new Student(name, age, idCardNumber);

        // 输入学生成绩
        while (true) {
            System.out.println("请输入课程名称（或输入 'exit' 结束）：");
            String subject = scanner.nextLine();

            if (subject.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("请输入 " + subject + " 的成绩：");
            int score = Integer.parseInt(scanner.nextLine());

            student.addScore(subject, score);
        }

        // 将学生信息保存到本地磁盘的 "Student.dat" 中
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Student.dat"))) {
            oos.writeObject(student);
            System.out.println("学生信息已成功保存到 Student.dat 文件中");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
