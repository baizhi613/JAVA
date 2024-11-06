package view;

import manager.ManagerOperation;
import user.UserOperation;

import java.lang.management.ManagementFactory;
import java.util.Scanner;

import static manager.ManagerOperation.managerEnroll;
import static user.UserOperation.userEnroll;

// 首页界面
public class StartView {
    /**
     * 负责人：
     * 功能：展示管理员、用户登录、注册、退出四个选项，
     *      按0退出，按1管理员登录，按2用户登录，按3进行注册，如果按错则打印出错请重新输入。
     */
    public static void menuView() {
        System.out.println("0:退出");
        System.out.println("1:管理员登录");
        System.out.println("2:用户登录");
        System.out.println("3:进行注册");
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("请输入你要选择的功能:");
            String st=sc.nextLine();
            switch (st){
                case "0":System.exit(0);
                         break;
                case "1":
                    ManagerOperation.initLogin();
                         break;
                case "2":
                    UserOperation.initLogin();
                         break;
                case "3":enroll();
                         break;
                default:System.out.println("无此项功能，请重新输入。");
                         continue;
            }
            break;
        }

    }

        /*
        例：
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();//字符串获取，避免用户产生输入异常
        switch(input) {
            case "0":
                调用管理员登录
            case "1":
                调用用户界面登录
            case "2":
                 退出
            case "3":
                 注册
            *//*
            3. 修炼
            4. 存档(打印敬请期待)
            5. 退出游戏
            6. 挑战boss
            *//*
            default: *//* 打印输入错误提示 *//*
        }*/


    /**
     * 负责人：
     * 功能介绍：进入注册界面，用户注册按1，管理员注册按2，
     *          并输入相应的账号和密码，最后提示注册成功返回登录界面
     *          （判断账号唯一性，若不唯一则报错）。如果输入其他数字则打印出错，并且可以实现重新输入
     */
    public static void enroll() {
        // 用户注册按1，管理员注册按2，
        // switch case进入对应页面
        System.out.println("1:用户注册");
        System.out.println("2:管理员注册");
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("请输入你要选择的功能:");
            String st=sc.nextLine();
            switch (st){
                case "1":UserOperation.userEnroll();
                    break;
                case "2":ManagerOperation.managerEnroll();
                    break;
                default:System.out.println("无此项功能，请重新输入。");
                    continue;
            }
            break;
        }

    }
 }

