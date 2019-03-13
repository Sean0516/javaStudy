package com.voicecyber.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Sean on 2018/12/11
 *
 * @author Sean
 */
public class FtpTest {
    public static FTPClient ftpClient;
    public static void main(String[] args) throws IOException {

        boolean sean = FtpTest.connect("192.168.4.104", 21, "User", "123456");
        boolean b = ftpClient.changeWorkingDirectory("/test/test1/test2");
        if (!b){
            createRemoteDir("/test/test1/test2/");
        }
        ftpClient.storeFile("/test/test1/test2/test.txt",new FileInputStream(new File("D:\\test.txt")));
    }
    public static boolean connect(String ip,Integer port,String userName,String passWord){
        boolean result=true;
        String localCharset = "GBK";
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                System.out.println("FTP目标服务器拒绝连接");
                return false;
            } else {
                ftpClient.login(userName, passWord);
                if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                    localCharset = "UTF-8";
                }
                ftpClient.setControlEncoding(localCharset);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//                boolean b = ftpClient.changeWorkingDirectory("/test/test1/");
//                if (!b){
//                    createRemoteDir("/test/test1/");
//                }
//                ftpClient.storeFile("/test/test1/test.txt",new FileInputStream(new File("D:\\test.txt")));
            }
        } catch (IOException e) {
            result=false;
            e.printStackTrace();
        }
        return result;
    }

    public static boolean createRemoteDir( String remoteDir ) {
        String str = remoteDir;
        String[] items = str.split("/");
        String strPrev = "";
        if( items.length > 0 )
        {
            for( int i = 0; i < items.length; i++ )
            {
                if( items[i].length() > 0)
                {
                    String dirname = "";

                    if( strPrev.length() > 0 )
                    {
                        dirname = strPrev+"/"+items[i];
                    }
                    else
                    {
                        int npos = remoteDir.indexOf( "/" );
                        if( npos == 0 )
                            dirname = "/"+items[i];
                        else
                            dirname = items[i];
                    }
                    try {
                        ftpClient.makeDirectory( dirname );
                        System.out.println(dirname);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    strPrev = dirname;
                }
            }
        }
        return  true;
    }
}
