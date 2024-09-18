package org.example;

public class Producer extends Thread{
    String name;
    Object lock;

    public Producer(String name, Object lock) {
        this.name = name;
        this.lock = lock;
    }

    public void produce() throws InterruptedException {
        synchronized (lock){
            while (true){
                if(Product.flag==true){
                    System.out.println("有产品，等待消费者消费");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName()+"没产品，准备生产");
                Product.flag=true;
                lock.notify();
            }
        }
    }

    @Override
    public void run(){
        try{
            produce();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}