package org.example;

public class ThreadUsedTest {
    public static void main(String[] args) throws InterruptedException {
//        PascalTriangleThread ptt=new PascalTriangleThread();
//        ptt.setName("打印杨辉三角线程");
//
//        FibonacciThread ft=new FibonacciThread();
//        Thread ftt=new Thread(ft);
//
//        ptt.start();
//        ftt.start();

//        FibonacciThread ft=new FibonacciThread();
//        Thread ftt=new Thread(ft);
//        System.out.println("新建线程时的状态："+ftt.getState());

//        ftt.start();
//        System.out.println("启动线程时的状态："+ftt.getState());
//
//        Thread.sleep(100);
//        System.out.println("计时等待时的状态："+ftt.getState());
//
//        Thread.sleep(1000);
//        System.out.println("等待线程时的状态："+ftt.getState());
//
//        ft.notifyNow();
//        System.out.println("唤醒线程时的状态："+ftt.getState());
//
//        Thread.sleep(1000);
//        System.out.println("线程终止时的状态："+ftt.getState());

        PascalTriangleThread ptt2=new PascalTriangleThread();
        FibonacciThread ft2=new FibonacciThread();
        Thread ftt2=new Thread(ft2);

        ftt2.setPriority(10);
        System.out.println("斐波那契数列线程的优先级："+ftt2.getPriority());
        ptt2.setPriority(1);
        System.out.println("杨辉三角形线程的优先级："+ptt2.getPriority());

        ptt2.start();
        ftt2.start();
    }
}
