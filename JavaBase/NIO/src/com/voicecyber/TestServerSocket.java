package com.voicecyber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Sean on 2018/5/31.
 */
public class TestServerSocket {
    public static void getServerSocket(Integer port) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
//        serverSocketChannel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel=serverSocketChannel.accept();
            new SocketClient(socketChannel);
        }
    }

    public static void main(String[] args) throws IOException {
        TestServerSocket.getServerSocket(8888);
    }
}
