package basicjavabean;
// 用户类
public class User extends Person {
    public User() {
    }

    public User(String account, String password, double money) {
        super(account, password, money);
    }
}
