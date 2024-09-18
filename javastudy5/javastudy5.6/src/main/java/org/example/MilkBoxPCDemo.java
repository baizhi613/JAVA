package org.example;

public class MilkBoxPCDemo {
    public static void main(String[] args) {
        MilkBox box=new MilkBox(10);
        Productor p=new Productor(box);
        Consumer c=new Consumer(box);
        Thread t1=new Thread(p,"送奶工-小王");
        Thread t2=new Thread(p,"送奶工-小李");
        Thread t3=new Thread(c,"爷爷 ");
        Thread t4=new Thread(c,"奶奶 ");
        t1.start();
        t2.start();
        t3.setDaemon(true);
        t4.setDaemon(true);
        t3.start();
        t4.start();
    }
}