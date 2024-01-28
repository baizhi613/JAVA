package org.itheima07;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo05 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName("org.itheima06.Student");

        Method[] methods = c.getDeclaredMethods();
        for(Method method:methods){
            System.out.println(method);
        }
        System.out.println("-----------");

        Method m=c.getMethod("method1");

        Constructor<?> con = c.getConstructor();
        Object obj=con.newInstance();

        m.invoke(obj);


    }
}
