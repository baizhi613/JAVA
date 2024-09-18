package org.example;

import java.math.BigDecimal;

/**
 * @author lys612411
 */
public class Test {
    public static void main(String[] args) {
        Commodity apple = new Commodity("1001", "苹果", BigDecimal.valueOf(10));
        Commodity banana = new Commodity("1002", "香蕉",BigDecimal.valueOf(5));
        Commodity orange = new Commodity("1003", "橘子",BigDecimal.valueOf(15));
        Commodity longyan = new Commodity("1004", "龙眼",BigDecimal.valueOf(4.5));

        Cart sc = new Cart();
        sc.add(apple, 10);
        sc.add(longyan, 2);
        sc.add(orange, 20);
        sc.add(banana, 3);
        sc.add(apple, 6);

        sc.remove(longyan, 10);
        sc.add(banana, 10);
        //sc.clear();
        sc.print();
    }
    }
