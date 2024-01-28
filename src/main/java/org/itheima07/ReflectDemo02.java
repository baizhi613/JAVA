package org.itheima07;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c=Class.forName("org.itheima06.Student");

        Constructor<?> con = c.getConstructor(String.class, int.class, String.class);

        Object obj=con.newInstance("liuyishan",20,"lanzhou");
        System.out.println(obj);

    }


}
