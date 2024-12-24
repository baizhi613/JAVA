package org.baizhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.baizhi.model.Subject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SubjectMapper {

    @Insert("INSERT INTO Subjects (subjectid, subjectname) VALUES (#{subjectid}, #{subjectname})")
    void insertSubject(Subject subject);

    @Select("SELECT * FROM Subjects")
    List<Subject> getAllSubjects();
}
