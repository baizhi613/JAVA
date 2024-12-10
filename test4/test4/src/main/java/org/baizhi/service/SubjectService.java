package org.baizhi.service;

import org.baizhi.mapper.SubjectMapper;
import org.baizhi.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    public void addSubject(Subject subject) {
        subjectMapper.insertSubject(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectMapper.getAllSubjects();
    }
}
