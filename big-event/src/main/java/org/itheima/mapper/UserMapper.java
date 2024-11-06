package org.itheima.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.itheima.pojo.User;

@Mapper
public interface UserMapper {
    // 修正了SQL语句中的参数占位符
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUserName(String username);

    // 修正了插入语句的参数占位符，并假设password参数被命名为md5String
    @Insert("INSERT INTO user(username, password, create_time, update_time) " +
            "VALUES(#{username}, #{md5String}, now(), now())")
    void add(String username, String md5String);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
