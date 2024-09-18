package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lys612411
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,Integer> map=new HashMap<>();
        map.put("张三",800);
        map.put("李四",1500);
        map.put("王五",3000);

        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println("Key = " + key + ", Value = " + value);
        }

        for (Integer value : map.values()) {
            System.out.println("Value = " + value);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        boolean containsKey = map.containsKey("张三"); // 返回true或false
        System.out.println(containsKey);

        map.remove("李四");

        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        // 使用增强型for循环遍历entries集合
        for (Map.Entry<String, Integer> entry : entries) {
            // 通过entry对象的getKey()和getValue()方法分别获取键和值
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}