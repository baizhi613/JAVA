package org.example;

public class Consumer implements Runnable {
    MilkBox b;
    String str;

    public Consumer(MilkBox box) {
        this.b = box;
    }

    @Override
    public void run() {
        while (true) {
            b.getMilk();
        }
    }
}
