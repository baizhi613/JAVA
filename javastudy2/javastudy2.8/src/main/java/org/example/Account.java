package org.example;

/**
 * @author lys612411
 */
public class Account {
    String AccountID;
    String userName;
    String passWord;
    double balance;

    public Account(String AccountID, String userName, String passWord, double balance){
        this.AccountID=AccountID;
        this.userName=userName;
        this.passWord=passWord;
        this.balance=balance;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}