package org.baizhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.baizhi.model.SeckillEvent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface EventMapper {

    // 查询所有秒杀事件
    @Select("SELECT * FROM seckill_event")
    List<SeckillEvent> getAllEvents();

    // 添加秒杀事件
    @Insert("INSERT INTO seckill_event(name, start_time, end_time, create_time, update_time) " +
            "VALUES(#{name}, #{startTime}, #{endTime}, NOW(), NOW())")
    void insertEvent(SeckillEvent seckillEvent);
}
