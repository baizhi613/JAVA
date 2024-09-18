package org.example;

import java.sql.*;

public class BankAdd {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
                "root","123456");

        String sql="select * from Account where AccountID=?";
        PreparedStatement ps=cn.prepareStatement(sql);
        ps.setString(1, "1001");
        ResultSet rs=ps.executeQuery();
        //rs.absolute(2);
        while(rs.next())
        {
            System.out.println(rs.getString(1)+": "+rs.getString(2)+
                    " "+rs.getInt(3));
        }
    }
}