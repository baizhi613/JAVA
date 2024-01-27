package org.itheima01;

public class myThreadDemo {
    public static void main(String[] args) {
        myThread my1=new myThread();
        myThread my2=new myThread();
        myThread my3=new myThread();

        my1.setName("高铁");
        my2.setName("飞机");
        my3.setName("汽车");

        System.out.println(my1.getPriority());
        System.out.println(my2.getPriority());
        System.out.println(my3.getPriority());

        my1.setPriority(5);
        my2.setPriority(10);
        my3.setPriority(1);

        my1.start();
        my2.start();
        my3.start();
    }
}
