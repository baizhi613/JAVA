package org.example;

public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    // 同步存款方法
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " 存款 " + amount + " 元, 当前余额: " + balance + " 元");
    }

    // 同步取款方法
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " 取款 " + amount + " 元, 当前余额: " + balance + " 元");
        } else {
            System.out.println(Thread.currentThread().getName() + " 取款失败, 余额不足");
        }
    }

    public int getBalance() {
        return balance;
    }
}