package com.voicecyber.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Sean on 2018/5/31.
 */
public class TcpServerSocket {
    public static void getServerSocket(Integer port) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        SocketChannel socketChannel=null;
        Executor executor= Executors.newCachedThreadPool();
        while (true){
            socketChannel =serverSocketChannel.accept();
            executor.execute(new SocketClient(socketChannel));
        }
    }

    public static void main(String[] args) throws IOException {
        TcpServerSocket.getServerSocket(8888);
    }
}
