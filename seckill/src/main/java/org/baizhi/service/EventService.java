package org.baizhi.service;

import org.baizhi.model.SeckillEvent;

import java.util.List;

public interface EventService {
    // 查询所有秒杀事件
    List<SeckillEvent> getAllEvents();

    // 添加秒杀事件
    void addEvent(SeckillEvent seckillEvent);
}
