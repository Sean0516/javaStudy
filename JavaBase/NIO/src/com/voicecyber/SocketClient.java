package com.voicecyber;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * Created by Sean on 2018/5/31.
 */
public class SocketClient implements  Runnable{
    private SocketChannel socketChannel;
    public SocketClient(SocketChannel socketChannel){
        this.socketChannel=socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        ByteBuffer toClient=ByteBuffer.allocate(1024);
        String str="this is server "+ LocalDateTime.now();
        try {
            int read = socketChannel.read(byteBuffer);
            while (read!=-1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.println((char) byteBuffer.get());
                }
                byteBuffer.put(str.getBytes());
                socketChannel.write(toClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
