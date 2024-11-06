package org.example;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lys612411
 */
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient String idCardNumber; // 确保身份证号不会被序列化
    private Map<String, Integer> scores;

    public Student() {
        scores = new HashMap<>();
    }

    public Student(String name, int age, String idCardNumber) {
        this.name = name;
        this.age = age;
        this.idCardNumber = idCardNumber;
        this.scores = new HashMap<>();
    }

    // getter 和 setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Map<String, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    public void addScore(String subject, int score) {
        this.scores.put(subject, score);
    }

    public Integer getScore(String subject) {
        return this.scores.get(subject);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", scores=" + scores +
                '}';
    }
}
