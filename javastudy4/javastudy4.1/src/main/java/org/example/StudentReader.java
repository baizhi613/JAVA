package org.example;

import java.io.*;
import java.util.Map;

/**
 * @author lys612411
 */
public class StudentReader {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Student.dat"))) {
            Student student = (Student) ois.readObject();
            displayStudentInfo(student);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void displayStudentInfo(Student student) {
        // 显示基本信息
        System.out.println("学生基本信息：");
        System.out.println("姓名：" + student.getName());
        System.out.println("年龄：" + student.getAge());

        // 显示成绩
        System.out.println("\n成绩：");
        Map<String, Integer> scores = student.getScores();
        double totalScore = 0.0;
        int subjectCount = scores.size();

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
            totalScore += entry.getValue();
        }

        // 计算并显示平均成绩
        double averageScore = subjectCount > 0 ? totalScore / subjectCount : 0.0;
        System.out.println("\n平均成绩：" + averageScore);
    }
}
