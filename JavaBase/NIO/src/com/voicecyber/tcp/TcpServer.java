package com.voicecyber.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Created by Sean on 2018/6/1.
 */
public class TcpServer {
    private Selector selector;
    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();

    public void initTcpServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listenClient() throws IOException {
        System.out.println("服务端启动");
        while (true) {
            int select = selector.select();
            if (select>0){
                Iterator<SelectionKey> selectionKeyIterator = this.selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        sayHello(socketChannel,"你好，这里是服务端");
                        socketChannel.register(this.selector, SelectionKey.OP_READ);
                    }  if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        readChannel(socketChannel);
                        socketChannel.register(this.selector,SelectionKey.OP_READ);
                    }
                    selectionKeyIterator.remove();
                }
            }
        }
    }

    public void readChannel(SocketChannel socketChannel) throws IOException {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(readBuffer);
        readBuffer.flip();
        System.out.println("接收到消息" + decoder.decode(readBuffer)+socketChannel.getRemoteAddress());
        readBuffer.clear();
    }
    public void sayHello(SocketChannel socketChannel,String str) throws IOException {
        socketChannel.write(ByteBuffer.wrap(str.getBytes()));
    }

    public static void main(String[] args) throws IOException {
        TcpServer tcpServer=new TcpServer();
        tcpServer.initTcpServer(8888);
        tcpServer.listenClient();
    }

}
