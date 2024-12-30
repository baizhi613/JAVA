package org.baizhi.controller;

import org.baizhi.model.User;
import org.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("用户不存在！");
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        int result = userService.createUser(user);
        return result > 0 ? "用户创建成功！" : "用户创建失败！";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        user.setUserId(id);
        int result = userService.updateUser(user);
        return result > 0 ? "用户更新成功！" : "用户更新失败！";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        int result = userService.deleteUser(id);
        return result > 0 ? "用户删除成功！" : "用户删除失败！";
    }
}
