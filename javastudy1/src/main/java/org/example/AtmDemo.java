package org.example;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author lys612411
 */
public class AtmDemo {
    static String[][] users=null;
    static double[] balance=null;
    static int currentAccount=-1;
    static void init()
    {
        System.out.println("ATM机正在初始化.......");
        String intial="1:jack:pwd1:3000.0|2:Jones:pwd2:2680.0|3:mark:pwd3:5980.0";
        String[] userData=intial.split("\\|");
        users=new String[userData.length][3];
        balance=new double[userData.length];
        for(int i=0;i<userData.length;i++){
            String[] user=userData[i].split(":");
            users[i][0]=user[1];
            users[i][1]=user[2];
            balance[i]=Double.parseDouble(user[3]);
        }
        System.out.println("ATM机初始化完成");
    }
    private static boolean checkIn(){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username=sc.next();
        System.out.println("请输入密码：");
        String password=sc.next();
        for(int i=0;i<users.length;i++){
            if(username.equals(users[i][0]) && password.equals(users[i][1])) {
                currentAccount=i;
                System.out.println("登录成功，欢迎" + username + "!");
                return true;
            }
        }
        System.out.println("用户名或密码输入错误！");
        return false;
    }//登录
    private static boolean withDraw(double amount){
            if(amount<balance[currentAccount]){
                balance[currentAccount]-=amount;
                return true;
            }else{
                return false;
            }
    }//取款
    private static void dePosit(double amount){
            balance[currentAccount]+=amount;
    }//存款
    private static double query(int accountID){
        return balance[accountID];
    }//查询余额
    private static void mainMenu()
    {
        int choice;
        Scanner s=new Scanner(System.in);

        while(true){
            System.out.println("");
            System.out.println("---欢迎使用 SHBank ATM机---");
            System.out.println("  **** 1.系统初始化\t****");
            System.out.println("  **** 2.用户操作\t****");
            System.out.println("  **** 3.系统管理\t****");
            System.out.println("  **** 0.退出\t****");
            System.out.println("");
            System.out.println("请选择您的操作：");
            choice=s.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("请稍等，正在为您初始化");
                    init();
                    break;
                case 2:
                    //System.out.println("请输入您的账号和密码：");
                    if(checkIn()){
                        subMenu();
                    }
                    break;
                case 3:System.out.println("正在建设中....");break;
                case 0:System.out.println("谢谢使用，再见！");currentAccount=-1;return;
                default:System.out.println("您的输入有误，请重新输入：");break;
            }
        }
    }
    private static void subMenu()
    {
        int choice;
        Scanner s=new Scanner(System.in);

        while(true){
            System.out.println("");
            System.out.println(users[currentAccount][0]+"\r--欢迎使用SHBankATM机--");
            System.out.println("  ****1.查询余额****");
            System.out.println("  ****2.取款****");
            System.out.println("  ****3.存款****");
            System.out.println("  ****0.退卡****");
            System.out.println("");

            System.out.println("请选择您需要的操作：");
            choice=s.nextInt();
            switch (choice)
            {
                case 1:
                    //System.out.println("请稍等，正在为您查询中......");
                    System.out.println("您账户余额为:"+balance[currentAccount]);
                    break;
                case 2:
                    //System.out.println("请稍等，正在为您取款中......");
                    System.out.println("请输入您要取的金额：");
                    double amount=s.nextDouble();
                    if(withDraw(amount)) {
                        System.out.println("取款成功，您的余额为:" + balance[currentAccount]);
                    }
                    else{
                        System.out.println("您的金额不足！");
                    }
                    break;
                case 3:
                    //System.out.println("请稍等，正在为您存款中......");
                    System.out.println("请输入您要存入的金额：");
                    double depAmount=s.nextDouble();
                    dePosit(depAmount);
                    System.out.println("存款成功，您的余额为:"+query(currentAccount));
                    break;
                case 0:
                    System.out.println("谢谢使用,"+users[currentAccount][0]+"请取出您 的银行卡");return;
                default:
                    System.out.println("您的输入有误，请重新输入：");break;
            }
        }
    }
    public static void main(String[] args){
        init();
        mainMenu();
    }
}