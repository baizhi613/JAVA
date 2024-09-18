package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MilkBox {
    int Max;//奶箱的容量
    List<String> box = Collections.synchronizedList(new ArrayList<>());

    public MilkBox(int max) {
        Max = max;
    }

    public synchronized String getMilk() {
        while (box.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String milk = box.remove(0);
        notifyAll(); // 通知等待的生产者可以生产了
        return milk;
    }
    public synchronized void putMilk(String m) {
        while (box.size() >= Max) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        box.add(m);
        notifyAll(); // 通知等待的消费者可以消费了
    }
}