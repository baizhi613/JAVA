package org.example;

public class Main {
    public static void main(String[] args) {
        Object lock=new Object();
        Consumer consumer=new Consumer("消费者",lock);
        Producer producer=new Producer("生产者",lock);
        consumer.start();
        producer.start();
    }
}
