package com.voicecyber.sftp;

import com.jcraft.jsch.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Sean on 2018/12/12
 *
 * @author Sean
 */
public class SFtpTest {
    private JSch jsch;
    private Session session = null;
    public static ChannelSftp channelSftp = null;
    private Channel channel = null;

    public static void main(String[] args) throws SftpException {
        SFtpTest sFtpTest = new SFtpTest();
        Thread thread=new Thread(()->{
            boolean root = sFtpTest.connect("192.168.6.5", 22, "root", "123456");
            System.out.println("是否连接："+root);
            int i=0;
            while (channelSftp!=null&&channelSftp.isConnected()){
                System.out.println("sftp is connected ");
                try {
                    InputStream inputStream = channelSftp.get("/media/infostore/src/500002201812180941500_rs.json");
                    Path path = Paths.get("D:\\test", "test"+i + ".json");
                    Files.copy(inputStream,path);
                    i++;
                } catch (SftpException e) {
                    if ( e.getCause()instanceof IOException){
                        System.out.println("io"+e.getCause().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //            boolean dirExist = sFtpTest.isDirExist("/test1/");
//            System.out.println(dirExist);
//            if (!dirExist) {
//            }
//            sFtpTest.uploadFile("c:/media/infostore/test/temp/500002201812141027220_0.wav","media/infostore/test/temp/500002201812141027220_0.wav");

    }

    public boolean connect(String ip, Integer port, String userName, String passWord) {
        jsch = new JSch();
        try {
            session = jsch.getSession(userName, ip, port);
        } catch (JSchException e) {
            System.out.printf("get sftp server ip %s port: %s session error as %s ", ip, port, e.getMessage());
            return false;
        }
        session.setPassword(passWord);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        try {
            session.connect();
        } catch (JSchException e) {
            System.out.printf("connect sftp server ip: %s port: %s  error as %s ", ip, port, e.getMessage());
            return false;
        }
        try {
            channel = session.openChannel("sftp");
            channel.connect();
        } catch (JSchException e) {
            System.out.printf("open  sftp server ip: %s port: %s channel error as %s ", ip, port, e.getMessage());
            return false;
        }
        channelSftp = (ChannelSftp) channel;
        return true;
    }

    public boolean uploadFile(String filePath, String remotePath,String remoteDir) {
        createDir(remoteDir);
        InputStream in = null;
            try {
                in = new FileInputStream(filePath);
                channelSftp.put(in,remotePath, ChannelSftp.OVERWRITE);
                int chmodInt = Integer.parseInt("777", 8);
                channelSftp.chmod(chmodInt, remotePath);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SftpException e) {
                e.printStackTrace();
            } finally {
                if (null!=in){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }


        return true;
    }

    public boolean isDirExist(String remoteDir) {
        try {
            channelSftp.cd(remoteDir);
        } catch (SftpException e) {
            System.out.printf("change work dir error as: %s ", e.getMessage());
            return false;
        }
        return true;
    }

    public boolean createDir(String remoteDir) {
        String str = remoteDir;
        String[] items = str.split("/");
        String strPrev = "";
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                if (items[i].length() > 0) {
                    String dirname = "";
                    if (strPrev.length() > 0) {
                        dirname = strPrev + "/" + items[i];
                    } else {
                        dirname = items[i];
                    }

                    try {
                        channelSftp.mkdir(dirname);
                        System.out.println(dirname);
                    } catch (SftpException e) {
                    }
                    strPrev = dirname;
                }
            }
        }
        return true;


    }

    public void disconnect() {
        if (channelSftp != null) {
            if (channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
        }
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
        jsch = null;
    }

}
