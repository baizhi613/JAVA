package org.example;

public class BankUser implements Runnable{
    private BankAccount account;
    private boolean depositOperation;

    public BankUser(BankAccount account, boolean depositOperation) {
        this.account = account;
        this.depositOperation = depositOperation;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            if (depositOperation) {
                account.deposit(1000);
            } else {
                account.withdraw(1000);
            }
            try {
                Thread.sleep(100); // 模拟处理时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
