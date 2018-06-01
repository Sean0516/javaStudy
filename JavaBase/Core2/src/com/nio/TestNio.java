package com.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Sean on 2018/5/31.
 */
public class TestNio {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
//        创建buffer
        ByteBuffer byteBuffer=ByteBuffer.allocate(6666);
        ByteBuffer byteBuffer1=ByteBuffer.allocateDirect(5555);
        FileInputStream fileInputStream=new FileInputStream("");
        FileChannel fileChannel=fileInputStream.getChannel();
        AsynchronousFileChannel asynchronousFileChannel=AsynchronousFileChannel.open(Paths.get(""));
        Future<Integer> read1 = asynchronousFileChannel.read(byteBuffer, 0);
        if (read1.isDone()){
            Integer integer = read1.get();
        }
    }
}
