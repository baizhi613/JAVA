package user;


import basicjavabean.Global;
import basicjavabean.User;
import manager.AddGoods;
import manager.QueryGoods;
import view.StartView;

import java.util.ArrayList;
import java.util.Scanner;

// 用户相关方法
public class UserOperation {
    public static User user = new User();

    public static void initLogin() {

        //记录当前用户id
        /*
        1:输入账号密码({
            成功：
                记录一下当前用户id,表示现在账户是谁
                （userMenu()）
            失败：（initLogin()）
        })
        0：退回初始登录界面(Test.initLogin();)
         */
        ArrayList<User> users = Global.users;
        System.out.println("1:输入账号密码");
        System.out.println("0:退回到初始登录界面");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你选择的功能:");
        String sum = sc.nextLine();
        if (sum.equals("1")) {
            System.out.println("请输入账号:");
            String accoun = sc.nextLine();
            int lk=0;
            for (int i = 0; i < Global.users.size(); i++) {
                if (users.get(i).getAccount().equals(accoun)) {
                    System.out.println("请输入密码:");
                    String password = sc.nextLine();
                    if (users.get(i).getPassword().equals(password)) {
                        user = users.get(i);
                        System.out.println("登录成功。");
                        userMenu(user);
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
        用户注册
        输入信息，创建用户对象
        获取用户集合（Test.users），循环集合判断是否包含该用户，
        如果包含则注册失败，否：注册成功-》添加到集合中（add方法）
     */
    public static void userEnroll() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账户:");
        String account = sc.nextLine();
        for (int i = 0; i < Global.users.size(); i++) {
            if (Global.users.get(i).getAccount().equals(account)) {
                System.out.println("已有此账户注册失败。");
                StartView.menuView();
            }
        }
        System.out.println("请输入账户密码:");
        String password = sc.nextLine();
        Global.users.add(new User(account, password, 0));
        System.out.println("注册成功。");
        initLogin();
    }

    private static void userMenu(User user) {
        /*
        1: 顾客采购（buyMenu();)
        2：客户账户（ accountInfo()）
        0：退出返回initLogin();
         */
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("0.退回登录界面\n1.顾客采购\n2.客户账户");
            int input01 = input.nextInt();
            switch (input01) {
                case 0:
                    initLogin();
                    break;
                case 1:
                    buyMenu(user);
                    break;
                case 2:
                    accountInfo(user);
                    break;
                default:
                    System.out.println("出错，请重新输入");
                    continue;
            }
            break;
        }
    }

    private static void accountInfo(User user) {
        /*
        1:充值recharge()
        2：余额查询queryBalance()
        0：退出（initLogin()）
         */
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("0.退回用户界面\n1.充值\n2.余额查询");
            int input01 = input.nextInt();
            switch (input01) {
                case 0:
                    userMenu(user);
                    break;
                case 1:
                    recharge(user);
                    break;
                case 2:
                    queryBalance(user);
                    break;
                default:
                    System.out.println("出错，请重新输入");
                    continue;
            }
            break;
        }
    }


    private static void recharge(User user) {
        /*
        输入充值金额并根据需求判断输入
        1：确认充值
        0：取消并返回（recharge()）
         */
        Scanner input = new Scanner(System.in);
        double input02;
        while (true) {
            System.out.println("请输入充值金额");
            input02 = input.nextDouble();
            if (input02 >= 0)
                break;
            else
                System.out.println("充值有误");
        }
        while (true) {
            System.out.println("0.取消并返回\n1.确认充值");
            int input03 = input.nextInt();

            if (input03 == 0) {
                queryBalance(user);
            } else if (input03 == 1) {
                user.setMoney(input02);
                accountInfo(user);
            } else {
                System.out.println("出错，请重新输入");
                continue;
            }
            break;
        }
    }

    private static void queryBalance(User user) {
        /*
        调用当前账户id查找到对应用户余额并输出
         */
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("0.返回\n1.查询");
            int input03 = input.nextInt();
            if (input03 == 0) {
                recharge(user);
            } else if (input03 == 1) {
                System.out.println(user.getMoney());
                accountInfo(user);
            } else {
                System.out.println("出错，请重新输入");
                continue;
            }
            break;
        }
    }

    public static void buyMenu(User user) {
        /*
        1：购买商品(buyGoods)
        2：查看商品（new QueryGoods()）
        0：退回登录界面(initLogin();)
         */
            System.out.println("1:购买商品");
            System.out.println("2:查看商品");
            System.out.println("0:退回到用户界面");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("请输入你选择的功能:");
                int sum = sc.nextInt();
                switch (sum) {
                    case 0:
                        userMenu(user);
                        break;
                    case 1:
                        buyGoods(user);
                        break;
                    case 2:
                        new QueryGoods();
                        break;
                    default:
                        System.out.println("没有此项功能,请重新输入。");
                        continue;
                }
                break;
            }
            userMenu(user);

        }


    private static void buyGoods(User user) {
        /*
        输入商品名，调用AddGoods中的方法查询商品是否在内（AddGoods.ifContains()）
        不在重来（buyGoods()）
        在内则输出出商品信息（goods[i].printGoodsInfo()）
        并选择是否支付：
            1：支付，扣除余额
            0：返回buyGoods()
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要购买的商品名称:");
        String name = sc.nextLine();
        boolean bo = AddGoods.ifGoodsContains(name);
        if (bo) {
            for (int i = 0; i < Global.goods.size(); i++) {
                if (name.equals(Global.goods.get(i).getName())) {
                    System.out.println("1:支付");
                    System.out.println("0:返回到购买商品");
                    String su = sc.nextLine();
                    if (su.equals("1")) {
                        double moneyl =user.getMoney() - Global.goods.get(i).getOutPrice();
                        user.setMoney(moneyl);
                    } else if (su.equals("0")) {
                        buyGoods(user);
                    }
                }
            }

        } else {
            System.out.println("没有此商品请重新输入。");
            buyGoods(user);
        }
        userMenu(user);
    }
}