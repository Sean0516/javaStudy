package com.voicecyber;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Sean on 2018/5/31.
 */
public class TestChannel {
    private static Charset charset = Charset.forName("UTF-8");
    private static CharsetDecoder decoder = charset.newDecoder();
    public static void readFileByChannel(String fileName) throws IOException {
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
    public static void writeFileByChannel(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String string = "hello timer ";
        buffer.put(string.getBytes());
        //此处必须要调用buffer的flip方法
        buffer.flip();
        channel.write(buffer);
        channel.close();
        outputStream.close();
    }
    public static void main(String[] args) throws IOException {
        TestChannel.writeFileByChannel("D:\\kafka.properties");
    }
}
