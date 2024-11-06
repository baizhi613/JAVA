package org.example;

import java.util.Date;
import java.util.List;

/**
 * @author lys612411
 */
public class User {
    private String name;
    private String password;
    private Date birthday;
    private String email;
    private List<User> friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "姓名='" + name + '\'' +
                ", 密码='" + password + '\'' +
                ", 出生日期=" + birthday +
                ", 邮箱='" + email + '\'' +
                ", 朋友=" + friends +
                '}';
    }
}
