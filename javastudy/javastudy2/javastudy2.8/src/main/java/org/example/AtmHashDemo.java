package org.example;

import org.example.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lys612411
 */
public class AtmHashDemo {
    static Map<String, Account> hm = new HashMap<>();

    public static void initAccounts() {
        hm.put("0001", new Account("0001", "Mark", "1234", 2345.7));
        hm.put("0002", new Account("0002", "Piggie", "1234", 8560.0));
        hm.put("0003", new Account("0003", "Judy", "1234", 7788.0));
        hm.put("0004", new Account("0004", "Jack", "1234", 200.0));
    }

    public static String checkin(String accountID, String userName, String passWord) {
        if (hm.containsKey(accountID)) {
            Account acc = hm.get(accountID);
            if (acc.getUserName().equals(userName) && acc.getPassWord().equals(passWord)) {
                return "登录成功，欢迎 " + userName + "!";
            } else {
                return "用户名或密码错误。";
            }
        } else {
            return "账户ID不存在。";
        }
    }

    public static void Deposit(String accountID, double amount) {
        if (hm.containsKey(accountID)) {
            Account acc = hm.get(accountID);
            double currentBalance = acc.getBalance();
            acc.setBalance(currentBalance + amount);
            System.out.println("存款成功。当前余额为：" + acc.getBalance());
        } else {
            System.out.println("账户ID不存在。");
        }
    }

    public static boolean WithDraw(String accountID, double amount) {
        if (hm.containsKey(accountID)) {
            Account acc = hm.get(accountID);
            double currentBalance = acc.getBalance();
            if (currentBalance >= amount) {
                acc.setBalance(currentBalance - amount);
                System.out.println("取款成功。当前余额为：" + acc.getBalance());
                return true;
            } else {
                System.out.println("余额不足。");
                return false;
            }
        } else {
            System.out.println("账户ID不存在。");
            return false;
        }
    }

    public static double Query(String accountID) {
        if (hm.containsKey(accountID)) {
            return hm.get(accountID).getBalance();
        } else {
            System.out.println("账户ID不存在。");
            return -1; // or any other suitable error indicator
        }
    }

    public static void main(String[] args) {
        initAccounts(); // 初始化账户数据

        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用 ATM 系统！");

        while (true) {
            System.out.print("请输入账户ID：");
            String accountID = scanner.next();

            System.out.print("请输入用户名：");
            String userName = scanner.next();

            System.out.print("请输入密码：");
            String password = scanner.next();

            String result = checkin(accountID, userName, password);
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
                            double balance = Query(accountID);
                            if (balance != -1) {
                                System.out.println("账户余额为：" + balance);
                            }
                            break;
                        case 2:
                            System.out.print("请输入存款金额：");
                            double depositAmount = scanner.nextDouble();
                            Deposit(accountID, depositAmount);
                            break;
                        case 3:
                            System.out.print("请输入取款金额：");
                            double withdrawAmount = scanner.nextDouble();
                            WithDraw(accountID, withdrawAmount);
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
