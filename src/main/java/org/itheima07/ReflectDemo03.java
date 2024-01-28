package org.itheima07;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo03 {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> c = Class.forName("org.itheima06.Student");

        Constructor<?> con = c.getDeclaredConstructor(String.class);

        con.setAccessible(true);

        Object obj=con.newInstance("liuyishan");
        System.out.println(obj);

    }
}
