package com.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sean on 2018/5/31.
 */
public class Tcp {
    public static void TcpClient() throws IOException {
        Socket socket=new Socket("192.168.65.0",8888);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (bufferedReader.readLine()!=null){
        }
    }
    public static void TcpServer() throws IOException {
        ServerSocket serverSocket=new ServerSocket(8888);
        Socket socket = serverSocket.accept();
//        一般都使用线程来创建客户端 ，因为客户端有多个
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }
}
