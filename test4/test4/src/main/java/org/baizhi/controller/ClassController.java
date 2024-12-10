package org.baizhi.controller;

import org.baizhi.model.Class;
import org.baizhi.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public void addClass(@RequestBody Class classObj) {
        classService.addClass(classObj);
    }

    @GetMapping
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }
}
