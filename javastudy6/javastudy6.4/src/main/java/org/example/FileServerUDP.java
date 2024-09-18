package org.example;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FileServerUDP {
    private static final int PORT = 10020;
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            byte[] receiveBuffer = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, BUFFER_SIZE);
            FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
            long totalBytesReceived = 0;

            long startTime = System.currentTimeMillis();

            while (true) {
                socket.receive(receivePacket);
                int bytesReceived = receivePacket.getLength();
                if (bytesReceived == -1) break;
                fileOutputStream.write(receiveBuffer, 0, bytesReceived);
                totalBytesReceived += bytesReceived;

                // Send acknowledgment
                String ack = "ACK";
                DatagramPacket ackPacket = new DatagramPacket(
                        ack.getBytes(), ack.getBytes().length,
                        receivePacket.getAddress(), receivePacket.getPort()
                );
                socket.send(ackPacket);
            }

            long endTime = System.currentTimeMillis();

            fileOutputStream.close();
            System.out.println("接收成功");
            System.out.println("接收字节数: " + totalBytesReceived);
            System.out.println("传输时间: " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
