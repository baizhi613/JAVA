package org.example;

/**
 * @author lys612411
 */
public class Student{
    private String name;
    private int number;
    private String sex;

    public Student(String name, int number, String sex) {
        this.name = name;
        this.number = number;
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", sex='" + sex + '\'' +
                '}';
    }
}