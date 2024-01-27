package org.itheima02;

public class myRunnableDemo {
    public static void main(String[] args) {
        myRunnable my1=new myRunnable();
        myRunnable my2=new myRunnable();

        Thread t1=new Thread(my1,"飞机");
        Thread t2=new Thread(my2,"高铁");

        t1.start();
        t2.start();
    }
}
