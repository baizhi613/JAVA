package org.example;

import java.sql.*;

public class BankAccountQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
                "root","123456");

        String sql = "SELECT * FROM Account WHERE accountID LIKE '10%'";

        PreparedStatement ps= cn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        while (rs.next()){
            String accountID = rs.getString("accountID");
            String userName = rs.getString("userName");
            double balance = rs.getDouble("balance");

            System.out.println("Account ID: " + accountID);
            System.out.println("Account Name: " + userName);
            System.out.println("Balance: " + balance);
            System.out.println("-----------------------");
        }

    }


}
