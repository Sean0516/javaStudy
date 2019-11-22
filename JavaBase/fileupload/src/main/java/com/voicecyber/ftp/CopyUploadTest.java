package com.voicecyber.ftp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * Created by Sean on 2018/12/17
 *
 * @author Sean
 */
public class CopyUploadTest {
    public static void main(String[] args) throws IOException {
        File file = new File("//192.168.6.222/share/media/infostore/src/500002201908271452460_rs.json");
        Files.copy(file.toPath(), Paths.get("D:\\timer.json"));
        System.out.println("copy end ");
//        if (file.exists()){
//            System.out.println(file.getAbsolutePath());
//        }
//        String localFile="C:\\media\\000\\20191017\\000\\50000020191017105759.mp3";
//        String ftpPath = localFile.substring(localFile.lastIndexOf("media") + 5);
//        System.out.println(ftpPath);

    }

}
