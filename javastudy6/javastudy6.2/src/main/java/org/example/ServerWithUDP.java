package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerWithUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = null;
        socket = new DatagramSocket(10020);

        byte[] data=new byte[1024];
        DatagramPacket packet=new DatagramPacket(data,data.length);

        socket.receive(packet);

        String info=new String(data,0,data.length);
        System.out.println("我是服务器，客户端说："+info);

        InetAddress address=packet.getAddress();
        int port=packet.getPort();
        byte[] data2="欢迎你！".getBytes();

        DatagramPacket packet2=new DatagramPacket(data2,data2.length,address,port);

        socket.send(packet2);

        socket.close();
    }
}
