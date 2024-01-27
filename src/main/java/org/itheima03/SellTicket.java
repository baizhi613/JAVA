package org.itheima03;

//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

public class SellTicket implements Runnable{
    private int tickets=100;
    private Object obj=new Object();
//    private Lock lock=new ReentrantLock();
//lock.lock();
//lock.unlock();
    @Override
    public void run(){
        while (true){
            synchronized (obj) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + tickets + "张票");
                    tickets--;
                }
            }
        }
    }
}
