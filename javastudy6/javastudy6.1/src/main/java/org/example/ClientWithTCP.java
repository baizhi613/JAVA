package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientWithTCP extends JFrame {
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;
    private JTextArea jtaShow;
    private JTextField jtfSend;

    public ClientWithTCP() {
        super();
        setTitle("客户端程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,379,260);

        final JPanel panel1=new JPanel();
        getContentPane().add(panel1, BorderLayout.NORTH);

        final JLabel label1=new JLabel("Client/server间聊天程序----客户端程序");
        label1.setForeground(new Color(0,0,255));
        label1.setFont(new Font("",Font.BOLD,22));
        panel1.add(label1);

        final JScrollPane scrollPane=new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        jtaShow=new JTextArea();
        scrollPane.setViewportView(jtaShow);

        final JPanel panel2=new JPanel();
        getContentPane().add(panel2, BorderLayout.SOUTH);

        final JLabel label=new JLabel("客户端发送的信息：");
        panel2.add(label);

        jtfSend=new JTextField();
        jtfSend.setPreferredSize(new Dimension(150,25));
        panel2.add(jtfSend);

        final JButton button=new JButton("发送");
        panel2.add(button);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(final ActionEvent e) {
                writer.println(jtfSend.getText());
                jtaShow.append("客户端发送的信息是："+jtfSend.getText()+"\n");
                jtfSend.setText("");
            }
        });
    }

    private void setConnect() throws IOException {
        socket =new Socket("127.0.0.1",1978);
        OutputStream os=socket.getOutputStream();
        writer=new PrintWriter(os,true);
        InputStream is=socket.getInputStream();
        InputStreamReader isr=new InputStreamReader(is);
        reader=new BufferedReader(isr);
        getServerInfo();
    }

    private void getServerInfo() throws IOException {
        try{
            while (true){
                if(reader!=null){
                    String line=reader.readLine();
                    if(line!=null)
                        jtaShow.append("接收到服务器发送的信息是："+line+"\n");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reader.close();
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        ClientWithTCP client=new ClientWithTCP();
        client.setVisible(true);
        client.setConnect();
    }
}
