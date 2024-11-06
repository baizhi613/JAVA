package org.example;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FileClientUDP {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 10020;
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        File file = new File("D:\\code\\JAVA\\javastudy6.4\\src\\main\\java\\org\\example\\test.txt");

        try (DatagramSocket socket = new DatagramSocket();
             FileInputStream fileInputStream = new FileInputStream(file)) {

            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
            byte[] sendBuffer = new byte[BUFFER_SIZE];
            int bytesRead;
            long totalBytesSent = 0;

            long startTime = System.currentTimeMillis();

            while ((bytesRead = fileInputStream.read(sendBuffer)) != -1) {
                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer, bytesRead, serverAddress, PORT
                );
                socket.send(sendPacket);
                totalBytesSent += bytesRead;

                byte[] ackBuffer = new byte[BUFFER_SIZE];
                DatagramPacket ackPacket = new DatagramPacket(ackBuffer, BUFFER_SIZE);
                socket.receive(ackPacket);
                String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());
                if (!"ACK".equals(ack)) {
                    System.out.println("Failed to receive ACK");
                    break;
                }
            }

            long endTime = System.currentTimeMillis();

            System.out.println("传输成功");
            System.out.println("发送字节数: " + totalBytesSent);
            System.out.println("传输时间: " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
