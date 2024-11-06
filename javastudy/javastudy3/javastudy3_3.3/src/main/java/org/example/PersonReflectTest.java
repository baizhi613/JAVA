package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lys612411
 */
public class PersonReflectTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz=null;
        try {
            clazz=Class.forName("org.example.Person");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("---获取所有属性---");
        Field[] fieldList=clazz.getDeclaredFields();
        for(Field f:fieldList){
            System.out.println(f);
        }

        System.out.println("---获取所有成员方法---");
        Method[] methodList=clazz.getDeclaredMethods();
        for(Method m:methodList){
            System.out.println(m);
        }

        System.out.println("---获取所有构造方法---");
        Constructor[] constructorList=clazz.getDeclaredConstructors();
        for(Constructor c:constructorList){
            System.out.println(c);
        }

        Person obj=(Person) clazz.newInstance();
        obj.setName("刘德华");
        obj.setSex("男");
        obj.setBirthday("1965-10-10");

        Method method=obj.getClass().getMethod("song",String.class,String.class);

        Object o=method.invoke(obj,"东方之珠","粤语版");
        System.out.println(obj.toString());
    }
}
