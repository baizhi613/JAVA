package org.baizhi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 代表一个秒杀事件，封装了事件的ID、名称、开始和结束时间以及记录的创建和更新时间。
 */
public class SeckillEvent {

    //秒杀事件Id。
    private Long id;
    //秒杀事件的名称。
    private String name;
    //秒杀事件的开始时间。
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    //秒杀事件的结束时间。
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    //秒杀事件记录的创建时间。
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //秒杀事件记录的最后更新时间。
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    // Getters and setters

    /**
     * 获取秒杀事件的唯一标识符。
     *
     * @return 秒杀事件的唯一标识符。
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置秒杀事件的唯一标识符。
     *
     * @param id 秒杀事件的唯一标识符。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取秒杀事件的名称。
     *
     * @return 秒杀事件的名称。
     */
    public String getName() {
        return name;
    }

    /**
     * 设置秒杀事件的名称。
     *
     * @param name 秒杀事件的名称。
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取秒杀事件的开始时间。
     *
     * @return 秒杀事件的开始时间。
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * 设置秒杀事件的开始时间。
     *
     * @param startTime 秒杀事件的开始时间。
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取秒杀事件的结束时间。
     *
     * @return 秒杀事件的结束时间。
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * 设置秒杀事件的结束时间。
     *
     * @param endTime 秒杀事件的结束时间。
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取秒杀事件记录的创建时间。
     *
     * @return 秒杀事件记录的创建时间。
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置秒杀事件记录的创建时间。
     *
     * @param createTime 秒杀事件记录的创建时间。
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取秒杀事件记录的最后更新时间。
     *
     * @return 秒杀事件记录的最后更新时间。
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置秒杀事件记录的最后更新时间。
     *
     * @param updateTime 秒杀事件记录的最后更新时间。
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
