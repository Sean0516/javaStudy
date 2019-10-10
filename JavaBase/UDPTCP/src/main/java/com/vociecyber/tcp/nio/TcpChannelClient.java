package com.vociecyber.tcp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Set;

public class TcpChannelClient {
    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();

    public void initClient(String ip, Integer port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        //        设置为非阻塞模式，立刻放回false ，表示连接正在建立
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(ip, port));
        Selector selector = Selector.open();
//        向channel 注册selector以及感兴趣的连接事件
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        SelectionKey selectionKey = null;
        while (true) {
            int select = selector.select();
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    if (key.isConnectable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        sc.configureBlocking(false);
//      注册感兴趣的IO 读事件，通常不直接注册写事件，在发送缓冲区未满的情况下，一直是科协的，因此如果注册了写事件，而又不用写数据，这样容易造成CPU 消耗100%
                        sc.register(selector, SelectionKey.OP_READ);
//                        完成连接建立
                        sc.finishConnect();

                    } else if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        readChannel(sc);
                    } else if (key.isWritable()) {
                        key.interestOps(key.interestOps() & (SelectionKey.OP_WRITE));
                        SocketChannel sc = (SocketChannel) key.channel();
                        sc.write(ByteBuffer.wrap("Hello Server ".getBytes()));
                    }

                }
            }
        }

    }

    private void readChannel(SocketChannel socketChannel) {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        try {

            while (socketChannel.read(readBuffer) != -1) {
                readBuffer.flip();
                System.out.println("get msg from tcp server: " + decoder.decode(readBuffer) + socketChannel.getRemoteAddress());
                readBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
