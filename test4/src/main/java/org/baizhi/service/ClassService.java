package org.baizhi.service;

import org.baizhi.mapper.ClassMapper;
import org.baizhi.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    public void addClass(Class classObj) {
        classMapper.insertClass(classObj);
    }

    public List<Class> getAllClasses() {
        return classMapper.getAllClasses();
    }
}
