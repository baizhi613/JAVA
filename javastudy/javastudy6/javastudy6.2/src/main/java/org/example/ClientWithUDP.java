package org.example;

import java.io.IOException;
import java.net.*;

public class ClientWithUDP {
    public static void main(String[] args) throws IOException {
        InetAddress address=null;
        address=InetAddress.getByName("127.0.0.1");
        int port=10020;
        byte[] data="用户名：admin;密码：123".getBytes();

        DatagramPacket packet=new DatagramPacket(data,data.length,address,port);

        DatagramSocket socket=new DatagramSocket();

        socket.send(packet);

        byte[] data2=new byte[1024];
        DatagramPacket packet2=new DatagramPacket(data2,data2.length);

        socket.receive(packet2);

        String reply=new String(data2,0,packet2.getLength());
        System.out.println("我是客户端，服务器说："+reply);

        socket.close();
    }
}