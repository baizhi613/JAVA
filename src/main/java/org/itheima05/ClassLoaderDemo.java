package org.itheima05;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader c=ClassLoader.getSystemClassLoader();
        System.out.println(c);

        ClassLoader c2=c.getParent();
        System.out.println(c2);

        ClassLoader c3=c2.getParent();
        System.out.println(c3);
    }
}
