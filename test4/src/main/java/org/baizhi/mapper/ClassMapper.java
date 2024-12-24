package org.baizhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.baizhi.model.Class;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ClassMapper {

    @Insert("INSERT INTO Classes (Classid, classname) VALUES (#{classid}, #{classname})")
    void insertClass(Class classObj);

    @Select("SELECT * FROM Classes")
    List<Class> getAllClasses();
}
