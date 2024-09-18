package manager;

import basicjavabean.Global;
import basicjavabean.Goods;

import java.util.Iterator;
import java.util.Scanner;

public class DeleteGoods {
    public DeleteGoods() {
        deleteGoods();
    }
    private void deleteGoods(){
        /*
        输入商品名
        调用AddGoods中的方法（AddGoods.ifGoodsContains(String name)），判断否存在
        不存在（new DeleteGoods）
        存在就在全局变量中移除该项数据（deleteGoods(name)）
        根据商品名查找，移除对应数据
         */
        Scanner sc=new Scanner(System.in);
        System.out.println("继续删除商品按1:\n退出按0:");
        System.out.println("请输入你的选择:");
        String sum=sc.nextLine();
        if (sum.equals("0")){
            ManagerOperation.managerMenu();
        } else if (sum.equals("1")) {
            System.out.println("请输入商品名字:");
            String name=sc.nextLine();
            boolean bo=AddGoods.ifGoodsContains(name);
            if (bo){
                for(int i=Global.goods.size()-1;i>=0;i--){
                    if (Global.goods.get(i).getName().equals(name)){
                        Global.goods.remove(i);
                        System.out.println("删除成功。");
                    }
                }
            }else {
                System.out.println("该商品不存在，请重新输入");
                new DeleteGoods();
            }
        }else {
            System.out.println("无此选项请重新输入");
            new DeleteGoods();
        }

        ManagerOperation.managerMenu();
    }
}
