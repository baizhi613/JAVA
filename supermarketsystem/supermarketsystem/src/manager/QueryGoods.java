package manager;

import basicjavabean.Global;
import user.UserOperation;

import java.util.Scanner;

public class QueryGoods {
    public QueryGoods() {
        queryGoods();
    }

    private void queryGoods(){
        /*
        1：输入商品名称查询对应商品，判断是否存在对应商品（AddGoods.ifGoodsContains(String name)）
        2：直接遍历所有商品
        0：退出到管理员界面(managerLogin.managerMenu())
         */
        System.out.println("查询对应商品按1:\n直接便利所有商品按2:\n退出到上层界面按0:");
        Scanner sc=new Scanner(System.in);
        String st;
        while (true){
            System.out.println("请输入你选择的功能:");
            String sum=sc.nextLine();
            if (sum.equals("0")){
                System.out.println("如果你是用户按1:\n如果你是管理员按2:");
                while (true){
                    System.out.println("如果你是用户按1:\n如果你是管理员按2:\n请输入你的选择");
                     st=sc.nextLine();
                    if (st.equals("1")){
                        UserOperation.buyMenu(UserOperation.user);
                    } else if (st.equals("2")) {
                        ManagerOperation.managerMenu();
                    }else {
                        System.out.println("没有此项功能请重新输入。");
                        continue;
                    }
                    break;
                }

            } else if (sum.equals("1")) {
                System.out.println("请输入对应商品的名称:");
                String name=sc.nextLine();
                boolean bo=AddGoods.ifGoodsContains(name);
                int t=0;
                if (bo){
                    for (int i = 0; i < Global.goods.size(); i++) {
                        if ( Global.goods.get(i).getName().equals(name)){
                            t=i;
                        }
                    }
                    System.out.println("编号："+Global.goods.get(t).getId()+","+
                            "商品名："+Global.goods.get(t).getName()+","+
                            "商品进价："+Global.goods.get(t).getInPrice()+","+
                            "商品出价："+Global.goods.get(t).getOutPrice()+
                            ","+"商品存货："+Global.goods.get(t).getStorage()+
                            ","+"商品打折："+Global.goods.get(t).getPromotion());
                    while (true){
                        System.out.println("如果你是用户按1:\n如果你是管理员按2:\n请输入你的选择");
                        st=sc.nextLine();
                        if (st.equals("1")){
                            UserOperation.buyMenu(UserOperation.user);
                        } else if (st.equals("2")) {
                            ManagerOperation.managerMenu();
                        }else {
                            System.out.println("没有此项功能请重新输入。");
                            continue;
                        }
                        break;
                    }
                }else {
                    System.out.println("没有该商品，已返回到查询商品界面，请重新输入：");
                    queryGoods();
                    break;
                }
            } else if (sum.equals("2")) {
                for (int i = 0; i < Global.goods.size(); i++) {
                    System.out.println("编号："+Global.goods.get(i).getId()+","+
                            "商品名："+Global.goods.get(i).getName()+","+
                            "商品进价："+Global.goods.get(i).getInPrice()+","+
                            "商品出价："+Global.goods.get(i).getOutPrice()+
                            ","+"商品存货："+Global.goods.get(i).getStorage()+
                            ","+"商品打折："+Global.goods.get(i).getPromotion());

                }
                while (true){
                    System.out.println("如果你是用户按1:\n如果你是管理员按2:\n请输入你的选择");
                     st=sc.nextLine();
                    if (st.equals("1")){
                        UserOperation.buyMenu(UserOperation.user);
                    } else if (st.equals("2")) {
                        ManagerOperation.managerMenu();
                    }else {
                        System.out.println("没有此项功能请重新输入。");
                        continue;
                    }
                    break;
                }
            }else {
                System.out.println("无此项功能请重新输入。");
                continue;
            }
            break;
        }


    }
}
