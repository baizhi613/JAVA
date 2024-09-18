package org.example;

import java.util.Random;

public class redpacket {
    public static void main(String[] args) {
        // 假设红包的最小金额为0.01元，最大金额为200元
        double minAmount = 0.01;
        double maxAmount = 200.00;

        // 创建一个Random对象
        Random random = new Random();

        // 生成一个随机的红包金额
        double redPacketAmount = minAmount + (random.nextDouble() * (maxAmount - minAmount));

        // 打印生成的红包金额
        System.out.println("生成的红包金额为：" + String.format("%.2f", redPacketAmount) + " 元");
    }
}
