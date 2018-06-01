package com.voicecyber.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Created by Sean on 2018/6/1.
 */
public class TcpClient {
    private Selector selector;
    private TcpServer tcpServer=new TcpServer();
    public void initClient(String ip,int port) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        this.selector=Selector.open();
        socketChannel.connect(new InetSocketAddress(ip,port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }
    public void listenServer()throws IOException{
        while (true){
            int select = selector.select();
            if (select > 0) {
                Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isConnectable()){
                       SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
                        if (socketChannel.isConnectionPending()){
                            socketChannel.finishConnect();
                        }
                        socketChannel.configureBlocking(false);
                        tcpServer.sayHello(socketChannel,"这里是客户端，你好服务端");
                        socketChannel.register(this.selector,SelectionKey.OP_READ);
                    }
                     if (selectionKey.isReadable()){
                        SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
                        tcpServer.readChannel(socketChannel);
                        socketChannel.register(this.selector,SelectionKey.OP_READ);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TcpClient tcpClient=new TcpClient();
        tcpClient.initClient("127.0.0.1",8888);
        tcpClient.listenServer();
    }
}
