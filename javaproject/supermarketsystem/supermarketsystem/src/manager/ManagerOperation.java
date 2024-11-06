package manager;

import basicjavabean.Global;
import basicjavabean.Manager;
import basicjavabean.User;
import view.StartView;

import java.util.ArrayList;
import java.util.Scanner;

// 管理员相关方法
public class ManagerOperation {
    public static Manager manager = new Manager();
    public static void initLogin(){
        /*
        1:输入账号密码({
            成功：
                记录一下当前用户id,表示现在账户是谁
                （managerMenu()）
            失败：（initLogin()）
        })
        0：退回初始登录界面(Test.initLogin();)
         */
        System.out.println("1:输入账号密码");
        System.out.println("0:退回到初始登录界面");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你选择的功能:");
        String sum = sc.nextLine();
        if (sum.equals("1")) {
            System.out.println("请输入账号:");
            String account = sc.nextLine();
            int lk=0;
            for (int i = 0; i < Global.managers.size(); i++) {
                if (Global.managers.get(i).getAccount().equals(account)) {
                    lk=2;
                    System.out.println("请输入密码:");
                    String password = sc.nextLine();
                    if (Global.managers.get(i).getPassword().equals(password)) {
                        manager=Global.managers.get(i);
                        System.out.println("登录成功。");
                        managerMenu();
                    } else {
                        System.out.println("密码错误请重新输入。");
                        initLogin();
                    }
                }

            }
            if (lk!=2){
                System.out.println("无此账号，请重新输入。");
                initLogin();
            }

        } else if (sum.equals("0")) {
            StartView.menuView();
        } else {
            System.out.println("输入既非0也非1请重新输入.");
            initLogin();
        }

    }
    /*
        管理员注册
        输入信息，创建管理员对象
        获取管理员集合（Test.managers），循环集合判断是否包含该管理员，
        如果包含则注册失败，否：注册成功-》添加到集合中（add方法）
     */
    public static void managerEnroll() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账户:");
        String account = sc.nextLine();
        for (int i = 0; i < Global.managers.size(); i++) {
            if (Global.managers.get(i).getAccount().equals(account)) {
                System.out.println("已有此账户注册失败。");
                StartView.menuView();
            }
        }
        System.out.println("请输入账户密码:");
        String password = sc.nextLine();
        Global.managers.add(new Manager(account, password, 0));
        System.out.println("注册成功。");
        initLogin();

    }

    public static void managerMenu(){
        /*
        1:销售管理（sales()）
        2：商品管理（managerGoods()）
        0：退回登录界面(initLogin();)
         */
        System.out.println("1:销售管理");
        System.out.println("2：商品管理");
        System.out.println("0：退回登录界面");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你选择的功能:");
            int sum = sc.nextInt();
            switch (sum) {
                case 0:
                    initLogin();
                    break;
                case 1:
                    sales();
                    break;
                case 2:
                    manageGoods();
                    break;
                default:
                    System.out.println("没有此项功能,请重新输入。");
                    continue;
            }
            break;
        }
    }

    private static void sales(){
        /*
        1:收银管理（cashier()）
        2：促销管理(promotion())
        3：返回管理员界面（managerMenu()）
         */
        System.out.println("1:收银管理");
        System.out.println("2：促销管理");
        System.out.println("0：返回管理员界面");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你选择的功能:");
            int sum = sc.nextInt();
            switch (sum) {
                case 1:
                    cashier();
                    break;
                case 2:
                    promotion();
                    break;
                case 0:
                    managerMenu();
                    break;
                default:
                    System.out.println("没有此项功能,请重新输入。");
                    continue;
            }
            break;
        }
    }

    private static void cashier(){
        //敬请期待
        Scanner sc = new Scanner(System.in);
        System.out.println("因系统功能未完全实现，如果想要继续使用其它功能按1:\n想要退出按0:");
        while (true) {
            System.out.println("请输入你选择的功能:");
            int ac = sc.nextInt();
            switch (ac) {
                case 0:
//                        System.exit(0);
                    managerMenu();
                    break;
                case 1:
//                        initLogin();
                    sales();
                    break;
                default:
                    System.out.println("没有此项功能,请重新输入。");
                    continue;
            }
            break;
        }
    }

    private static void promotion(){
        /*
        非核心内容
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("因系统功能未完全实现，如果想要继续使用其它功能按1:\n想要退出按0:");
        while (true) {
            System.out.println("请输入你选择的功能:");
            int ac = sc.nextInt();
            switch (ac) {
                case 0:
//                        System.exit(0);
                    managerMenu();
                    break;
                case 1:
//                        initLogin();
                    sales();
                    break;
                default:
                    System.out.println("没有此项功能,请重新输入。");
                    continue;
            }
            break;
        }
    }

    private static void manageGoods(){
        /*
        1: 增（new AddGoods()）
        2：删（new DeleteGoods()）
        3：改 (new updateGoods())
        4：查 （new QueryGoods()）
        0：3：返回管理员界面（managerMenu()）
         */
        System.out.println("1: 增\n" +
                "2：删\n" +
                "3：改 \n" +
                "4：查 \n" +
                "0：返回管理员界面");
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("请输入你要选择的功能:");
            int sum=sc.nextInt();
            switch (sum){
                case 0:managerMenu();
                     break;
                case 1:new AddGoods();
                    break;
                case 2:new DeleteGoods();
                    break;
                case 3:new updateGoods();
                    break;
                case 4:new QueryGoods();
                    break;
                default:System.out.println("无此项功能请重新输入。");
                    continue;
            }
            break;
        }

    }
}
