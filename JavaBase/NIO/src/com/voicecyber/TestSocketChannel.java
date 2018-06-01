package com.voicecyber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * Created by Sean on 2018/5/31.
 */
public class TestSocketChannel {
    public static void getSocketChannel(String url,Integer port) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(url, port));
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        ByteBuffer allocate = ByteBuffer.allocate(512);
        while (true){
            int read = socketChannel.read(byteBuffer);
            while (read!=-1){
                System.out.println(read);
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){

                }
                byteBuffer.clear();
                read=socketChannel.read(byteBuffer);
            }
            String str="this is from client " + LocalDateTime.now();
            allocate.put(str.getBytes());
            allocate.flip();
            while (allocate.hasRemaining()){
                socketChannel.write(allocate);
            }
            if (read==-1){
                socketChannel.close();
                break;
            }
        }


    }

    public static void main(String[] args) throws IOException {
        TestSocketChannel.getSocketChannel("192.168.6.80",8888);

    }
}
