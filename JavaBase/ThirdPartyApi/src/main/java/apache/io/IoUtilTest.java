package apache.io;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.sshd.common.util.io.IoUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * @author sean
 * @date 2019/12/16  14:21
 */
public class IoUtilTest {
    public static void main(String[] args) throws IOException {
//        文件拷贝
//        Files.copy(null,null);
//        IoUtils.copy(new FileInputStream(""),new FileOutputStream(""));

//        文件读取问 byte 数组
//        FileInputStream fileInputStream=new FileInputStream("D:\\test.txt");
//        byte[] bytes=new byte[1024];
//        IoUtils.read(fileInputStream,bytes);
//        System.out.println(new String(bytes,"UTF-8"));
//        byte[] bytes = Files.readAllBytes(null);
//        byte[] bytes = IoUtils.toByteArray(null);

//        将文件按照每行读取 ，区别在 读取的文件源
//        List<String> stringList = Files.readAllLines(null);
//        List<String> stringList1 = com.google.common.io.Files.readLines(null, Charset.defaultCharset());
//        List<String> stringList = IoUtils.readAllLines(new FileInputStream(""));

//        清楚文件夹下的所有文件
//        FileUtils.cleanDirectory(null);
//        递归的删除目录
//        FileUtils.deleteDirectory(null);

//        FileUtils.write();

//        目录,文件拷贝
//        FileUtils.copyDirectory(new File("src"),new File("target"));
//        FileUtils.copyFile()
//        FileUtils.copyURLToFile();
//        当虚拟机退出时，删除该文件
//            FileUtils.forceDeleteOnExit(new File(""));

        }
}
