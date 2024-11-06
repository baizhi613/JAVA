package org.itheima.springbootquickstart.service.impl;

import org.itheima.springbootquickstart.mapper.UserMapper;
import org.itheima.springbootquickstart.pojo.User;
import org.itheima.springbootquickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
