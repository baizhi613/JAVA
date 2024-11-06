package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileTypeAnalysis {
    public static void main(String[] args) {
        Path filePath = Paths.get("D:\\code\\JAVA\\javastudy6.3\\src\\main\\java\\org\\example\\test.txt");

        try {
            String fileType = Files.probeContentType(filePath);
            System.out.println("文件类型: " + fileType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
