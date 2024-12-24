package org.baizhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.baizhi.model.Elective;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ElectiveMapper {

    @Insert("INSERT INTO Elective (Studentid, subjectid, grade) " +
            "VALUES (#{studentid}, #{subjectid}, #{grade})")
    void insertElective(Elective elective);

    @Select("SELECT * FROM Elective")
    List<Elective> getAllElectives();
}
