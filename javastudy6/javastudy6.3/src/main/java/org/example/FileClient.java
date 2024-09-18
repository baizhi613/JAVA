package org.example;

import java.io.*;
import java.net.Socket;

public class FileClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 10020;

    public static void main(String[] args) {
        File file = new File("D:\\code\\JAVA\\javastudy6.3\\src\\main\\java\\org\\example\\test.txt");

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;

            long startTime = System.currentTimeMillis();

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }

            long endTime = System.currentTimeMillis();

            fileInputStream.close();
            socket.close();

            System.out.println("文件传输成功");
            System.out.println("发送的字节总数: " + totalBytesRead);
            System.out.println("传输时间: " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
