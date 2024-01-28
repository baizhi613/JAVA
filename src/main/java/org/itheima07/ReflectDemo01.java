package org.itheima07;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo01 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName("org.itheima06.Student");

        Constructor<?>[] cons = c.getDeclaredConstructors();
        for(Constructor con:cons){
            System.out.println(con);
        }
        System.out.println("---------");

        Constructor<?> con=c.getConstructor();

        Object obj=con.newInstance();
        System.out.println(obj);
    }
}
