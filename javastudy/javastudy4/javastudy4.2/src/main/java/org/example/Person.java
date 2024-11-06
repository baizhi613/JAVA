package org.example;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lys612411
 */
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private transient String cardID;
    private String sex;
    private int age;

    public Person() {
    }
    public Person(String name, String cardID, String sex, int age) {
        this.name = name;
        this.cardID = cardID;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cardID='" + cardID + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}