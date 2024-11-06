package org.example;

import java.sql.*;

public class update {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
                "root","123456");

        //PreparedStatement ps= cn.prepareStatement(sql);
        //ResultSet rs=ps.executeQuery();
//        String sql="insert into Account(AccountID,UserName,passWord,balance) values(?,?,?,?)";
//        PreparedStatement ps=cn.prepareStatement(sql);
//        ps.setString(1, "1004");
//        ps.setString(2,"liuyishan");
//        ps.setString(3,"123");
//        ps.setDouble(4,5000);
//        int count=ps.executeUpdate();

        System.out.println("========After Insert=======");
        //getAll();
        String sql1="delete from  Account where userName=?";
        PreparedStatement ps1= cn.prepareStatement(sql1);
        PreparedStatement ps=cn.prepareStatement(sql1);
        ps.setString(1, "1004");
        int count1=ps.executeUpdate();

        System.out.println("========After delete=======");
        String sql2;
        sql2="update Account set balance=balance-? where accountID=?";
        ps=cn.prepareStatement(sql2);
        ps.setString(1,"1004");
        int count2=ps.executeUpdate();
        System.out.println("========After update=======");

    }
}
