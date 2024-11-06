package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Bank?allowPublicKeyRetrieval=true",
                "root", "123456");//建立连接

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("select * from Account");

        while(rs.next())
        {
            System.out.println(rs.getString(1)+": "+rs.getString(2)+"\t"
                    +rs.getInt(3)+"\t"+rs.getDouble(4));
            System.out.println(rs.getString("AccountID")+":"+rs.getString("UserName")+" "
                    +rs.getInt("password")+rs.getDouble("balance"));
        }
        //最后记得释放连接
        rs.close();
        st.close();
        conn.close();
    }
}