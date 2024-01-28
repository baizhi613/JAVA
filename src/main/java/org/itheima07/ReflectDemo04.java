package org.itheima07;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo04 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName("org.itheima06.Student");

        Field[] fields=c.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field);
        }
        System.out.println("-----------");

        Field addrssField=c.getField("address");

        Constructor<?> con = c.getConstructor();
        Object obj=con.newInstance();

        addrssField.set(obj,"xianan");
        System.out.println(obj);
    }
}
