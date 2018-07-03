package com.voicecyber;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Sean on 2018/5/31.
 */
public class TestChannel {
    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();
    public static void redaFileByChannel(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        int read = channel.read(byteBuffer);
        while (read != -1) {
            byteBuffer.flip();
                System.out.println("____"+decoder.decode(byteBuffer));
            byteBuffer.clear();
            read = channel.read(byteBuffer);
        }
        fileInputStream.close();
        channel.close();
    }

    public static void readFileByChannelOfManyBuffer(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer header = ByteBuffer.allocate(12);
        ByteBuffer body = ByteBuffer.allocate(512);
        ByteBuffer[] byteBuffers = {header, body};
        long read = channel.read(byteBuffers);
        while (read != -1) {
            System.out.println(read);
            header.clear();
            body.clear();
            read = channel.read(byteBuffers);
        }

        channel.close();
        fileInputStream.close();
    }

    public static void transferChannelToFileChannel(String fromFile, String toFile) throws IOException {
//        SocketChannel socketChannel=SocketChannel.open();
        FileInputStream fileInputStream = new FileInputStream(fromFile);
        FileChannel fromChannel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream(toFile);
        FileChannel toChannel = fileOutputStream.getChannel();
        toChannel.transferFrom(fromChannel, 0, fromChannel.size());
        fromChannel.close();
        toChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
    public static void transferFileChannelToOtherChannel(String fromFile, String toFile) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(fromFile);
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream(toFile);
        FileChannel toChannel = fileOutputStream.getChannel();
        channel.transferTo(0,channel.size(),toChannel);
        channel.close();
        toChannel.close();

    }
    public static  void selector() throws IOException {
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("192.136",8888));
        // 设置为非阻塞模式 ，所以file channel 不能和selector一起使用
        socketChannel.configureBlocking(false);
        Selector selector=Selector.open();
        socketChannel.register(selector,SelectionKey.OP_READ);
        int select = selector.select();


    }

    public static void main(String[] args) throws IOException {
        TestChannel.redaFileByChannel("D:\\kafka.properties");


    }
}
