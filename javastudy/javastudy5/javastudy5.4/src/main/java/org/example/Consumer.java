package org.example;

public class Consumer extends Thread{
    String name;
    Object lock;

    public Consumer(String name, Object lock) {
        this.name=name;
        this.lock=lock;
    }

    public void consume() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(Product.flag==false){
                    System.out.println("没有产品消费，等待生产者生产！");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName()+"有产品了，消费者开始消费");
                Product.flag=false;
                lock.notify();
            }
        }
    }

    @Override
    public void run(){
        try{
            consume();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
