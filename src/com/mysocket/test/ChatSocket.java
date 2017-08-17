package com.mysocket.test;

import java.io.*;
import java.net.Socket;

/**
 * Created by tong on 17-6-16.
 */
public class ChatSocket implements Runnable{
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private Socket socket;

    public ChatSocket(Socket socket){
        this.socket = socket;
        try {
            //获取客户端的输入流对象
            InputStream is = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is,"utf-8"));
            //获取输出流对象
            OutputStream os = socket.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送消息
    public void out(String message){
        try {
            if (bufferedWriter == null || message == null) return;
            bufferedWriter.write(message);
            bufferedWriter.write("\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            //接收读取的数据,即客户端发送过来的数据
            String line;
            while ((line = bufferedReader.readLine())!=null){
                System.out.println("客户端发送的消息:" + line);
                MannagerSocket.getInstance().publish(this,line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
