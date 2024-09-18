package org.example;

public class BankSimulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(0);

        Thread user1 = new Thread(new BankUser(account, true), "用户1");
        Thread user2 = new Thread(new BankUser(account, true), "用户2");
        Thread user3 = new Thread(new BankUser(account, false), "用户3");

        user1.start();
        user2.start();
        user3.start();

        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最终余额: " + account.getBalance() + " 元");
    }
}