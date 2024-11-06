import basicjavabean.Global;
import basicjavabean.Goods;
import basicjavabean.Manager;
import basicjavabean.User;
import view.StartView;

import java.util.ArrayList;

public class Main {
    /**
     * 登录注册信息对该集合操作
     */

    public static void main(String[] args) {
        ArrayList<Manager> managers= Global.managers;
        ArrayList<User> users= Global.users;
        ArrayList<Goods> goods=Global.goods;
        /**
         * 初始化玩家登录信息
         */
        managers.add(new Manager("zhangsan", "123",10000));
        managers.add(new Manager("lisi", "456",20000));
        users.add(new User("wang5", "789",100));
        users.add(new User("wang6", "789",200));
        users.add(new User("wang7", "789",300));

        /**
         * 初始化全部商品
         */
        goods.add(new Goods("1","卫龙",5, 10, 100, 1));
        // 自行添加
        /*goods.add(new Goods("1","卫龙",5, 10, 100, 1));
        goods.add(new Goods("1","卫龙",5, 10, 100, 1));
        goods.add(new Goods("1","卫龙",5, 10, 100, 1));*/

        // 进入系统
        StartView.menuView();
    }
}
