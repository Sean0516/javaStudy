package com.vociecyber.tcp.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Sean on 2018/6/1.
 */
public class TcpServer {
    public static void createTcpServer(int port) throws IOException {
//        创建服务端套接字
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = null;
        Executor executorService = Executors.newCachedThreadPool();
        boolean flag = true;
        while (flag) {
//            等待客户端连接 ，当客户端连接后 ，返回一个socket 实例
            clientSocket = serverSocket.accept();
            System.out.println("客户端请求 ，开启socket");
//            开启一个新的线程。来处理新接入的客户端socket
            executorService.execute(new TcpSocketThread(clientSocket));
        }
        serverSocket.close();
        System.out.println("服务端关闭");
    }

    public static void main(String[] args) throws IOException {
        TcpServer.createTcpServer(8888);
    }
}
