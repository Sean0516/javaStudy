package com.io;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 2018/5/25.
 */
public class FileOpreate {
//    Files 类的使用;
public static void main(String[] args) throws IOException {

//    读文件
    Path path= Paths.get("D:\\kafka.yml");
//    将文件转化为字节数组
    byte[] bytes=Files.readAllBytes(path);
//    将字节数组转化为字符串
    String str = new String(bytes ,"UTF-8");
//    将文件转化为 行序列
    List<String> stringList=Files.readAllLines(path);
    String s="timer sean12";
    List<String> lines=new ArrayList<>();
    lines.add("123");
    lines.add("132213");
//    写文件  写行的集合
    Files.write(path,lines,StandardOpenOption.APPEND);
//    写文件 写字符串
    Files.write(path,s.getBytes("UTF-8"));
//    写文件 写字符串 追加
    Files.write(path,s.getBytes("UTF-8"),StandardOpenOption.APPEND);

//    复制文件
    Path path1=Paths.get("D:\\kafka2.yml");
    Files.copy(path,path1);
    Files.copy(path,path1,StandardCopyOption.REPLACE_EXISTING);
//    移动文件
    Files.move(path,path1);
//     删除文件
    Files.delete(path);  //如果不存在 ，抛出异常
    Files.deleteIfExists(path); //如果不存在 ，返回false

//    创建文件和目录
    Files.createDirectory(path);//使用改方法 ，路径中除了第一个目录，其他部分必须是存在的。 如果要创建中间路径目录，可以使用下面方法
    Files.createDirectories(path);
    Files.createFile(path);//创建文件，如果文件存在，则抛出异常， 文件不存在，文件不存在，文件被创建
    Files.createTempDirectory(path,""); //创建临时目录
    Files.createTempFile("",""); //创建临时文件
//    遍历文件
    DirectoryStream<Path> directoryStream=Files.newDirectoryStream(path,"");
    for (Path path2 : directoryStream) {
    }
    //文件枷锁  ，当多应用需要修改文件时可以使用
//    FileChannel fileChannel=FileChannel.open(path);
//    fileChannel.lock();
//    FileLock fileLock = fileChannel.tryLock();
//    fileLock.close(); //释放锁

}



}
