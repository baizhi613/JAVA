package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author lys612411
 */
public class Student {
    private String name;
    private int age;
    private Map<String, Integer> scores;

    @JsonIgnore
    private String idCard; // 身份证号，禁止序列化

    // Getters and setters
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("age")
    public int getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(int age) {
        this.age = age;
    }

    @JsonProperty("scores")
    public Map<String, Integer> getScores() {
        return scores;
    }

    @JsonProperty("scores")
    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
