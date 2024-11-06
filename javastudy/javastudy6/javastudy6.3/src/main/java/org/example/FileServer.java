package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    private static final int PORT = 10020;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("连接客户端....");

                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                FileOutputStream fileOutputStream = new FileOutputStream("received_file");
                byte[] buffer = new byte[4096];
                int bytesRead;
                long totalBytesRead = 0;

                long startTime = System.currentTimeMillis();

                while ((bytesRead = dataInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                }

                long endTime = System.currentTimeMillis();

                fileOutputStream.close();
                socket.close();

                System.out.println("文件传输成功");
                System.out.println("读到的字节总数: " + totalBytesRead);
                System.out.println("传输时间: " + (endTime - startTime) + " ms");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
