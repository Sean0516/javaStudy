package com.voicecyber.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * Created by Sean on 2018/5/31.
 */
public class TcpClientSocketChannel {
    public static void getSocketChannel(String ip, Integer port) throws IOException {
        SocketChannel clientChannel = SocketChannel.open();
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";
        clientChannel.connect(new InetSocketAddress(ip, port));
        ByteBuffer readBuffer = ByteBuffer.allocate(12);
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        boolean flag = true;
        while (flag) {
            System.out.println("输入需要发送的消息");
            msg = userInput.readLine();
            if (msg.equals("bye")) {
                break;
            }
//            writeBuffer.put(msg.getBytes());
//            if (writeBuffer.hasRemaining()) {
//                clientChannel.write(writeBuffer);
//            }
//            writeBuffer.clear();
            int read = clientChannel.read(readBuffer);
            while (read != -1) {
                readBuffer.flip();
                System.out.println("来自服务端消息：" + readBuffer.asCharBuffer());
                readBuffer.clear();
                read = clientChannel.read(readBuffer);
            }
        }
        clientChannel.close();

    }

    public static void main(String[] args) throws IOException {
        TcpClientSocketChannel.getSocketChannel("localhost", 8888);

    }
}
