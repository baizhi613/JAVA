package manager;


import basicjavabean.Global;
import basicjavabean.Goods;

import java.util.ArrayList;
import java.util.Scanner;
import manager.ManagerOperation;
public class AddGoods {
    public AddGoods() {
        addGoods();
    }
    public static boolean ifGoodsContains(String name){
        /*
        将输入的name与集合中的元素进行比较,已经存在返回true,不存在则返回false
         */
        for(int i=0;i<Global.goods.size();i++){
            if (Global.goods.get(i).getName().equals(name)){
               return true;
            }
        }
        return false;
    }

    private void addGoods(){
        /*
        输入要添加的商品信息
        检查商品名字唯一性(ifGoodsContains(String name))
        已经存在则重来（new AddGoods()）
        不存在则继续输入剩下的商品信息
        调用全局变量Test.goods，将检查完毕后的商品加入添加进Test.goods
         */
        Scanner sc=new Scanner(System.in);
        System.out.println("继续添加商品按1:\n退出按0:");
        System.out.println("请输入你的选择:");
        String sum=sc.nextLine();
        if (sum.equals("0")){
            ManagerOperation.managerMenu();
        }
        else if (sum.equals("1")){
            System.out.println("请输入商品名字:");
            String name=sc.nextLine();
            boolean bo=ifGoodsContains(name);
            if (bo){
                System.out.println("商品已经存在，请重新输入其它商品。");
                new AddGoods();
            }else {
                System.out.println("请输入商品编号:");
                String id=sc.nextLine();
                System.out.println("请输入商品进价:");
                double inPrice=sc.nextDouble();
                System.out.println("请输入商品出价:");
                double outPrice=sc.nextDouble();
                System.out.println("请输入商品存货:");
                int storage=sc.nextInt();
                System.out.println("请输入商品打折情况:");
                double promotion=sc.nextDouble();
                Global.goods.add(new Goods(id,name,inPrice,outPrice,storage,promotion));
                System.out.println("商品添加成功。");
            }
        }else {
            System.out.println("无此选项请重新输入");
            addGoods();
        }
        ManagerOperation.managerMenu();
    }
}
