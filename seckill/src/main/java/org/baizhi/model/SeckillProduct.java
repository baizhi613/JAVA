package org.baizhi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 秒杀商品模型类
 * 该类包含了秒杀商品的基本信息，如商品ID、名称、描述、价格、库存以及秒杀活动的开始和结束时间等
 */
public class SeckillProduct implements Serializable{

    // 商品ID
    private Long id;
    // 商品名称
    private String name;
    // 商品描述
    private String description;
    // 商品价格
    private BigDecimal price;
    // 商品库存
    private Integer stock;
    // 秒杀活动开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    // 秒杀活动结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    // 商品创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    // 商品更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // Getters and setters
    /**
     * 获取商品ID
     * @return 商品ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置商品ID
     * @param id 商品ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     * @return 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品描述
     * @return 商品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商品描述
     * @param description 商品描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取商品价格
     * @return 商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     * @param price 商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取商品库存
     * @return 商品库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置商品库存
     * @param stock 商品库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取秒杀活动开始时间
     * @return 秒杀活动开始时间
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * 设置秒杀活动开始时间
     * @param startTime 秒杀活动开始时间
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取秒杀活动结束时间
     * @return 秒杀活动结束时间
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * 设置秒杀活动结束时间
     * @param endTime 秒杀活动结束时间
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取商品创建时间
     * @return 商品创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置商品创建时间
     * @param createTime 商品创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取商品更新时间
     * @return 商品更新时间
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置商品更新时间
     * @param updateTime 商品更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
