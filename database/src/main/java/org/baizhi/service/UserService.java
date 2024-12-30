package org.baizhi.service;

import org.baizhi.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Integer userId);
    List<User> getAllUsers();
    int createUser(User user);
    int updateUser(User user);
    int deleteUser(Integer userId);
}
