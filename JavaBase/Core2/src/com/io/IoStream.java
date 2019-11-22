package com.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Sean on 2018/5/25.
 */
public class IoStream {
    public static void readFileByStream(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        int tempByte;
        while ((tempByte = bufferedInputStream.read()) != -1) {
            System.out.println(tempByte + "_____");
        }
        fileInputStream.close();
        bufferedInputStream.close();
    }

    public static void readFileByReader(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            System.out.println(temp + "_____");
        }
        fileReader.close();
        bufferedReader.close();
    }

    public static void writeFileByStream(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
        String ss = "你好";
        byte[] bytes = ss.getBytes();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public static void writeFileByWriter(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.newLine();
        bufferedWriter.write("This is a timer");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void streamToReader(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        fileInputStream.close();
        bufferedReader.close();
    }

    public static void writeTextWriter(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);
//        FileOutputStream fileOutputStream=new FileOutputStream(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);
        printWriter.println("1231");
        printWriter.close();
    }

    public static void readZipFile(String zipFileName) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName));
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            System.out.println(entry.getName());
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

    public static void writeZipFile(String zipFileName, String[] fileNames) throws IOException {

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
        for (String fileName : fileNames) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }

    public static void writeObjectFile(String fileName, Object... objects) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutputStream.writeObject(objects);
        objectOutputStream.close();
    }

    public static void readObjectFile(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        Demo[] demos = (Demo[]) objectInputStream.readObject();
        for (Demo demo : demos) {
            System.out.println(demo.toString());
        }
        objectInputStream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

    }
}
