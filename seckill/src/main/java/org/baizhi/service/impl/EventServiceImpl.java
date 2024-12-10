package org.baizhi.service.impl;

import org.baizhi.mapper.EventMapper;
import org.baizhi.model.SeckillEvent;
import org.baizhi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;
    @Autowired
    public EventServiceImpl(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Override
    public List<SeckillEvent> getAllEvents() {
        return eventMapper.getAllEvents();
    }

    @Override
    public void addEvent(SeckillEvent seckillEvent) {
        eventMapper.insertEvent(seckillEvent);
    }
}
