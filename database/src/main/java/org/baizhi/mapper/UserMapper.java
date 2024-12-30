package org.baizhi.mapper;

import org.baizhi.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Users WHERE userId = #{userId}")
    User getUserById(Integer userId);

    @Select("SELECT * FROM Users")
    List<User> getAllUsers();

    @Insert("INSERT INTO Users (username, password, email, role, createdAt) " +
            "VALUES (#{username}, #{password}, #{email}, #{role}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Update("UPDATE Users SET username = #{username}, password = #{password}, email = #{email}, role = #{role} WHERE userId = #{userId}")
    int updateUser(User user);

    @Delete("DELETE FROM Users WHERE userId = #{userId}")
    int deleteUser(Integer userId);
}
