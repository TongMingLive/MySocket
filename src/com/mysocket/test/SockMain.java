package com.mysocket.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tong on 17-6-12.
 */
public class SockMain {

    public static void main(String[] args) {
        try {
            //创建ServerSocket对象,提供端口号12345,供客户端访问,主机ip就是本机ip地址
            ServerSocket serverSocket = new ServerSocket(12345);
            //等待客户端连接,此时处于阻塞状态
            System.out.println("等待客户端连接");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("有客户端连接上来了");
                //获取客户端的主机ip
                String address = socket.getInetAddress().getHostAddress();
                System.out.println("客户端的ip:" + address);

                ChatSocket chatSocket = new ChatSocket(socket);
                new Thread(chatSocket).start();
                //将客户端对象添加到容器中
                MannagerSocket.getInstance().add(chatSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
