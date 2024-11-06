package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author lys612411
 */
public class AtmArrayListDemo {
    static ArrayList<Account> accounts=new ArrayList<>();
    public static void initAccounts()
    {
        accounts.add(new Account("0001","Mark","1234",2345.7));
        accounts.add(new Account("0002","Piggie","1234",8560.0));
        accounts.add(new Account("0003","Judy","1234",7788.0));
        accounts.add(new Account("0004","Jack","1234",200.0));
    }

    public static String checkin(String userName, String passWord) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName) && account.getPassWord().equals(passWord)) {
                return "登录成功，欢迎 " + account.getUserName() + "！";
            }
        }
        return "用户名或密码错误，请重试。";
    }
    public static void Deposit(String accountID, double amount) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                account.setBalance(account.getBalance() + amount);
                System.out.println("存款成功，" + account.getUserName() + "的账户余额为: " + account.getBalance());
                return;
            }
        }
        System.out.println("账户ID不存在，请检查输入。");
    }
    public static boolean WithDraw(String accountID,double amount){
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    System.out.println("取款成功，" + account.getUserName() + "的账户余额为: " + account.getBalance());
                    return true;
                } else {
                    System.out.println("余额不足，无法取款。");
                    return false;
                }
            }
        }
        System.out.println("账户ID不存在，请检查输入。");
        return false;
    }
    public static double Query(String accountID) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                return account.getBalance();
            }
        }
        System.out.println("账户ID不存在，请检查输入。");
        return -1;
    }

    public static double Query(String userName, String passWord) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName) && account.getPassWord().equals(passWord)) {
                return account.getBalance();
            }
        }
        System.out.println("用户名或密码错误，请检查输入。");
        return -1;
    }

    public static void main(String[] args) {
        initAccounts(); // 初始化账户数据

        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用 ATM 系统！");

        while (true) {
            System.out.println("情输入账户ID：");
            String accountID = scanner.next();

            System.out.print("请输入用户名：");
            String userName = scanner.next();

            System.out.print("请输入密码：");
            String password = scanner.next();

            String result = checkin(userName, password);
            System.out.println(result);

            if (result.startsWith("登录成功")) {
                while (true) {
                    System.out.println("请选择操作：");
                    System.out.println("1. 查询余额");
                    System.out.println("2. 存款");
                    System.out.println("3. 取款");
                    System.out.println("4. 退出");

                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            double balance = Query(userName, password);
                            if (balance != -1) {
                                System.out.println("账户余额为：" + balance);
                            }
                            break;
                        case 2:
                            System.out.print("请输入存款金额：");
                            double depositAmount = scanner.nextDouble();
                            Deposit(accountID,depositAmount);
                            break;
                        case 3:
                            System.out.print("请输入取款金额：");
                            double withdrawAmount = scanner.nextDouble();
                            WithDraw(accountID,withdrawAmount);
                            break;
                        case 4:
                            System.out.println("谢谢使用，再见！");
                            return;
                        default:
                            System.out.println("无效的选择，请重新输入。");
                            break;
                    }
                }
            }
        }
    }
}
