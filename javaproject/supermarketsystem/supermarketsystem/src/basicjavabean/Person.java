package basicjavabean;

public class Person {
    private String account;
    private String password;
    // 金钱
    private double money;
    public Person() {
    }

    public Person(String account, String password, double money) {
        this.account = account;
        this.password = password;
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money += money;
    }
}
