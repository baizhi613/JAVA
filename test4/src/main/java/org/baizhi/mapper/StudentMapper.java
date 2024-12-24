package org.baizhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.baizhi.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO Students (Studentid, classid, studentname, gender, graduated) " +
            "VALUES (#{studentid}, #{classid}, #{studentname}, #{gender}, #{graduated})")
    void insertStudent(Student student);

    @Select("SELECT * FROM Students")
    List<Student> getAllStudents();
}
