package org.example;

import com.mysql.cj.MysqlType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class process {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = null;
        String url = "jdbc:mysql://localhost:3306/bank";
        //服务器地址，端口，数据库名
        String userName = "root";   //数据库用户名
        String pwd = "123456";     // 数据库密码
        cn = DriverManager.getConnection(url, userName, pwd);
        String  sql = "{call ageGTx(?,?)}";//构造调用存储过程的SQL
        CallableStatement callableStatement = cn.prepareCall(sql);
        //赋值
        int agex=25;
        callableStatement.setInt(1,agex);
        //对于out参数，声明
        callableStatement.registerOutParameter(2, MysqlType.FIELD_TYPE_INT24);
        //调用
        callableStatement.execute();
        //取值
        int countAge=callableStatement.getInt(2);

        System.out.printf("年龄大于%d岁的用户有%d个",agex,countAge);
        callableStatement.close();
        cn.close();
    }
}
