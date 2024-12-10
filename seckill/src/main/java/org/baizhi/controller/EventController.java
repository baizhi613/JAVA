package org.baizhi.controller;

import org.baizhi.model.SeckillEvent;
import org.baizhi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seckill/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // 查询所有秒杀事件
    @GetMapping("/list")
    public List<SeckillEvent> getAllEvents() {
        return eventService.getAllEvents();
    }

    // 添加秒杀事件
    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestBody SeckillEvent seckillEvent) {
        try {
            eventService.addEvent(seckillEvent);
            return ResponseEntity.ok("秒杀事件添加成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("秒杀事件添加失败：" + e.getMessage());
        }
    }
}
