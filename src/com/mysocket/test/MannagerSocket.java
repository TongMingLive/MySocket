package com.mysocket.test;

import java.util.Vector;

/**
 * Created by tong on 17-6-16.
 */
public class MannagerSocket {
    //存储客户端对象的容器
    private Vector<ChatSocket> vector = new Vector<>();

    private static MannagerSocket mannagerSocket = null;
    private MannagerSocket(){}
    public static MannagerSocket getInstance(){
        if (mannagerSocket == null)
            mannagerSocket = new MannagerSocket();
        return mannagerSocket;
    }

    //添加客户端到容器中
    public void add(ChatSocket chatSocket){
        vector.add(chatSocket);
    }

    //删除客户端
    public void remove(ChatSocket chatSocket){
        vector.remove(chatSocket);
    }

    //消息内容的分发
    public void publish(ChatSocket chatSocket,String message){
        for (ChatSocket chat : vector){
            if (chat.equals((chatSocket))) continue;
            chat.out(message);
        }
    }
}
