package org.baizhi.service;

import org.baizhi.mapper.ElectiveMapper;
import org.baizhi.model.Elective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectiveService {

    @Autowired
    private ElectiveMapper electiveMapper;

    public void addElective(Elective elective) {
        electiveMapper.insertElective(elective);
    }

    public List<Elective> getAllElectives() {
        return electiveMapper.getAllElectives();
    }
}
