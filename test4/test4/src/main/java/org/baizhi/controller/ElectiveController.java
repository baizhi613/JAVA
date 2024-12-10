package org.baizhi.controller;

import org.baizhi.model.Elective;
import org.baizhi.service.ElectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electives")
public class ElectiveController {

    @Autowired
    private ElectiveService electiveService;

    @PostMapping
    public void addElective(@RequestBody Elective elective) {
        electiveService.addElective(elective);
    }

    @GetMapping
    public List<Elective> getAllElectives() {
        return electiveService.getAllElectives();
    }
}
