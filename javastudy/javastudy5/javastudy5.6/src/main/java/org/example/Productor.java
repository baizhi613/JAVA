package org.example;

public class Productor implements Runnable{
    MilkBox b;//送奶工要将牛奶送入奶箱,
    int index=0;
    String str="";//锁对象
    public Productor(MilkBox box) {
        b=box;
    }
    @Override
    public void run() {
        synchronized (str) {
            while(index<30){//假设用户订了30瓶牛奶
                ++index;
                b.putMilk("光明" + index + "号");
            }
        }
    }
}