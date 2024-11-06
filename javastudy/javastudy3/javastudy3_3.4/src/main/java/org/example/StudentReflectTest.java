package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lys612411
 */
public class StudentReflectTest {
    public static void main(String[] args) {
        Class<?> clazz=null;
        try {
            clazz=Class.forName("org.example.Student");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("---所有属性---");
        Field[] fields= clazz.getDeclaredFields();
        for(Field f:fields){
            System.out.println(f);
        }

        System.out.println("---所有成员方法---");
        Method[] methods=clazz.getDeclaredMethods();
        for(Method m:methods){
            System.out.println(m);
        }

        System.out.println("---所有构造方法---");
        Constructor[] constructors=clazz.getDeclaredConstructors();
        for(Constructor c:constructors){
            System.out.println(c);
        }
    }
}
