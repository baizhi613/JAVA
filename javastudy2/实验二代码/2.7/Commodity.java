package org.example;

import java.math.BigDecimal;

/**
 * @author lys612411
 */
public class Commodity {

    // 商品编号
    private String id;
    // 商品名称
    private String name;
    // 商品价格 跟金融有关的数据我们一般用BigDecimal这个数据类型
    private BigDecimal price;

    public Commodity(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Commodity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}