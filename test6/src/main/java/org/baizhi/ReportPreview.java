package org.baizhi;

import net.sf.jasperreports.engine.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportPreview {
    public static void main(String[] args) {
        try {
            // 加载 Jasper 报表文件
            JasperReport jasperReport = JasperCompileManager.compileReport("D:\\code\\JAVA\\test6\\src\\main\\java\\org\\baizhi\\master_report.jasper");

            // 建立数据库连接（根据你的数据库类型进行修改）
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test6", "root", "123456");

            // 创建参数 map
            Map<String, Object> parameters = new HashMap<>();

            // 填充报表
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            // 关闭数据库连接
            connection.close();

            // 使用 JasperPrintManager 进行打印，第二个参数设置为 true 表示显示打印对话框
            JasperPrintManager.printReport(jasperPrint, true);

        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }
}