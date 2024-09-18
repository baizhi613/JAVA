package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lys612411
 */
public class Cart {
    // 存放商品信息和数量
    Map<Commodity, Integer> commodityMap;
    BigDecimal totalPrice = BigDecimal.valueOf(0.0);
    public Cart() {
        commodityMap = new HashMap<>();
    }
    // 向购物车中添加商品
    public void add(Commodity commodity, int num) {
        // 检查购物车中是否包含当前商品
        if (commodityMap.containsKey(commodity)) {
            // 如果包含，取得之前购物车中的商品数量
            int existingNum = commodityMap.get(commodity);
            // 更新购物车商品数量
            commodityMap.put(commodity, existingNum + num);
        } else {
            // 如果不包含，直接添加
            commodityMap.put(commodity, num);
        }

        // 更新总价
        BigDecimal itemTotalPrice = commodity.getPrice().multiply(BigDecimal.valueOf(num));
        totalPrice = totalPrice.add(itemTotalPrice);
    }

    public void remove(Commodity commodity, int num) {
        // 检查购物车中是否包含当前商品
        if (commodityMap.containsKey(commodity)) {
            // 获取购物车中的商品数量
            int existingNum = commodityMap.get(commodity);

            // 判断移除 num 个后商品数量
            if (existingNum <= num) {
                // 如果移除后的数量小于等于 0，将该商品删除
                commodityMap.remove(commodity);
                // 更新总价
                BigDecimal itemTotalPrice = commodity.getPrice().multiply(BigDecimal.valueOf(existingNum));
                totalPrice = totalPrice.subtract(itemTotalPrice);
            } else {
                // 否则，更新商品数量
                commodityMap.put(commodity, existingNum - num);
                // 更新总价
                BigDecimal itemTotalPrice = commodity.getPrice().multiply(BigDecimal.valueOf(num));
                totalPrice = totalPrice.subtract(itemTotalPrice);
            }
        } else {
            System.out.println("购物车中不存在该商品");
        }
    }

    public void clear() {
        // 清空购物车
        commodityMap.clear();
        // 将总价设置为 0
        totalPrice = BigDecimal.valueOf(0.0);
    }

    public void print() {
        System.out.println("-------------购物车商品清单------------\r 商品编号\t商品名称\t单价\t数量\t小计");
        System.out.println("No.\tName\tPrice\tMount\tsubTotal");

        // 遍历购物车中的商品并打印详细信息
        for (Map.Entry<Commodity, Integer> entry : commodityMap.entrySet()) {
            Commodity commodity = entry.getKey();
            int quantity = entry.getValue();
            BigDecimal subTotal = commodity.getPrice().multiply(BigDecimal.valueOf(quantity));

            System.out.println(
                    commodity.getId() + "\t" +
                            commodity.getName() + "\t" +
                            commodity.getPrice() + "\t" +
                            quantity + "\t" +
                            subTotal
            );
        }
        System.out.println("\r-------------总价：" + totalPrice);
    }

}