package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author lys612411
 */
public class JsonDeserializerDemo {
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John Doe\",\"age\":20,\"scores\":{\"Math\":85,\"English\":90,\"Science\":95},\"idCard\":\"1234567890\"}";

        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 反序列化 JSON 字符串
            Student student = objectMapper.readValue(jsonString, Student.class);

            // 显示反序列化结果
            System.out.println("姓名：" + student.getName());
            System.out.println("年龄：" + student.getAge());
            System.out.println("成绩：");
            for (Map.Entry<String, Integer> entry : student.getScores().entrySet()) {
                System.out.println(entry.getKey() + "：" + entry.getValue());
            }
            // 由于 idCard 字段被 @JsonIgnore 修饰，不会被反序列化
            System.out.println("身份证号：" + student.getIdCard());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
