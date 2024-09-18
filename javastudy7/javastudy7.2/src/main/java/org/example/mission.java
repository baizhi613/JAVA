package org.example;

import java.sql.*;

public class mission {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
                "root","123456");
        cn.setAutoCommit(false);//默认情况下，事务会自动提交
        String sql;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            sql = "update Account set balance=balance-? where AccountID=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, 10);
            ps.setString(2, "1001");
            ps.executeUpdate();
            sql = "update Account set balance=balance+? where AccountID=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, 10);
            ps.setString(2, "1008");
            ps.executeUpdate();
            cn.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            try {
                //回滚事务
                cn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("转账失败");
        } finally {

            ps.close();
            cn.close();
        }
    }
}
