package manager;

import basicjavabean.Global;

import java.util.Scanner;

public class updateGoods {
    public updateGoods() {
        updateGoods();
    }

    private void updateGoods(){
        /*
        输入商品名，判断是否存在
        不存在（updateGoods()）
        存在输入要修改的信息进行更改
         */
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入商品名称:");
        String name=sc.nextLine();
        boolean bo=AddGoods.ifGoodsContains(name);
        if (bo){
            int t=0;
            System.out.println("修改id按1:\n修改名字按2:\n修改进价按3:\n修改出价4:\n修改存货按5:\n修改促销按6:");
            for (int i = 0; i < Global.goods.size(); i++) {
                if ( Global.goods.get(i).getName().equals(name)){
                    t=i;
                }
            }
          while (true){
              System.out.println("请重新输入你要修改的选项:");
              String iu=sc.nextLine();
              if (iu.equals("1")){
                  System.out.println("请重新输入你要修改为什么:");
                  String sa=sc.nextLine();
                  Global.goods.get(t).setId(sa);
                  System.out.println("修改成功");
              } else if (iu.equals("2")){
                  System.out.println("请重新输入你要修改为什么:");
                  String sb=sc.nextLine();
                  Global.goods.get(t).setName(sb);
                  System.out.println("修改成功");
              } else if (iu.equals("3")) {
                  System.out.println("请重新输入你要修改为什么:");
                  double sd=sc.nextDouble();
                  Global.goods.get(t).setInPrice(sd);
                  System.out.println("修改成功");
              } else if (iu.equals("4")) {
                  System.out.println("请重新输入你要修改为什么:");
                  double se=sc.nextDouble();
                  Global.goods.get(t).setOutPrice(se);
                  System.out.println("修改成功");
              } else if (iu.equals("5")) {
                  System.out.println("请重新输入你要修改为什么:");
                  int sf=sc.nextInt();
                  Global.goods.get(t).setStorage(sf);
                  System.out.println("修改成功");
              } else if (iu.equals("6")) {
                  System.out.println("请重新输入你要修改为什么:");
                  double sj=sc.nextDouble();
                  Global.goods.get(t).setPromotion(sj);
                  System.out.println("修改成功");
              }else {
                  System.out.println("没有此项功能请重新输入。");
                  continue;
              }
              break;
          }
        }else {
            System.out.println("此商品不存在,请重新输入。");
            updateGoods();
        }
        ManagerOperation.managerMenu();
    }
}
