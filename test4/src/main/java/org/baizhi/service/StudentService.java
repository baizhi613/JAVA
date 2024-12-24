package org.baizhi.service;

import org.baizhi.mapper.StudentMapper;
import org.baizhi.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public void addStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }
}
