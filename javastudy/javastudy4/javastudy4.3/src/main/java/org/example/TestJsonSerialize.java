package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author lys612411
 */
public class TestJsonSerialize {
    public static void main(String[] args) throws IOException {
        try{
            new TestJsonSerialize().start();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    public void start() throws IOException, ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");

        User u=new User();
        List<User> friends=new ArrayList<>();
        u.setName("张三");
        u.setPassword("123456");
        u.setBirthday(formatter.parse("19901009"));
        u.setEmail("zhangsan@qq.com");
        u.setFriends(friends);

        User f1=new User();
        f1.setName("李四");
        f1.setPassword("123456");
        f1.setBirthday(formatter.parse("19891009"));
        f1.setEmail("lisi@qq.com");
        User f2=new User();
        f2.setName("王五");
        f2.setPassword("123456");
        f2.setBirthday(formatter.parse("19881009"));
        f2.setEmail("wangwu@qq.com");

        ObjectMapper mapper=new ObjectMapper();
        String jsonObject=mapper.writeValueAsString(u);
        System.out.println("---------------------");
        System.out.println("java对象转json");
        System.out.println(jsonObject);
        System.out.println("---------------------");

        friends.add(f1);
        friends.add(f2);
        String json=mapper.writeValueAsString(u);
        System.out.println("---------------------");
        System.out.println("java数组对象混合JSON");
        System.out.println(json);
        System.out.println("---------------------");

        String jsonString="{\"name\":\"张三\",\"password\":\"123456\",\"birthday\":566410600000,\"email\":\"zhangsan@qq.com\",\"friends\":[       ]}";
        ObjectMapper mapper2=new ObjectMapper();
        User user=mapper2.readValue(jsonString,User.class);
        System.out.println(user.toString());
    }
}
