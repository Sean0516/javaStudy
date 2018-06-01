package com.voicecyber.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * Created by Sean on 2018/5/31.
 */
public class SocketClient implements Runnable {
    private SocketChannel socketChannel;

    public SocketClient(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer toClient = ByteBuffer.allocate(1024);
        String str = "";
        try {
            while (true) {
                int read = socketChannel.read(readBuffer);
                while (read != -1) {
                    readBuffer.flip();
                    System.out.println("来自客户端的消息：" + readBuffer.asCharBuffer());
                    readBuffer.clear();
                    read = socketChannel.read(readBuffer);
                }
                str = "this is server " + LocalDateTime.now();
                toClient.put(str.getBytes());
                if (toClient.hasRemaining()) {
                    socketChannel.write(toClient);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
