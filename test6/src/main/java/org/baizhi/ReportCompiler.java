package org.baizhi;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class ReportCompiler {
    public static void main(String[] args) {
        try {
            // 编译主报表
            JasperCompileManager.compileReportToFile("D:\\code\\JAVA\\test6\\src\\main\\java\\org\\baizhi\\master_report.jrxml");
            // 编译子报表
            JasperCompileManager.compileReportToFile("D:\\code\\JAVA\\test6\\src\\main\\java\\org\\baizhi\\master_report.jrxml");
            System.out.println("Reports compiled successfully.");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}