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
        File file = new File("//192.168.6.25/home/media/");
        System.out.println("1111");
        System.out.println(System.currentTimeMillis());
        if (!file.exists()) {
            Files.createDirectories(file.toPath());
        }
        System.out.println(System.currentTimeMillis());
        System.out.println("end ");
    }

}
