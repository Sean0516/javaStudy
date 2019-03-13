package com.voicecyber.ftp;/*
package com.duplicall.vclog.core.utils.upload;

import com.vclog.persistence.model.UploadConnection;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

*/
/**
 * Created by hu on 2018-01-15.
 *//*

public class FtpUpload implements IUpload {

    public static FTPClient ftpClient = null;
    // 本地编码字符串编码
    private String localCharset = "GBK";
    // FTP服务器端字符串编码
    private String serverCharset = "ISO-8859-1";

    private static Logger logger = LoggerFactory.getLogger(FtpUpload.class);

    */
/**
     * @Description: 获取FTPClient对象
     *//*

    public boolean connect(UploadConnection upload) {
        boolean flag = false;
        ftpClient = new FTPClient();
        int reply;
        try {
            ftpClient.connect(upload.getIp(), Integer.parseInt(upload.getPort()));
            System.out.print(ftpClient.getReplyString());
            reply = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.error("FTP目标服务器积极拒绝.");
            } else {
                ftpClient.login(upload.getUserName(), upload.getPassword());
                // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
                if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                    localCharset = "UTF-8";
                }
                ftpClient.setControlEncoding(localCharset);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.changeWorkingDirectory(upload.getBasePath());
                flag = true;
            }
        } catch (IOException e) {
            logger.error("连接FTP异常", e.getMessage());
        }
        logger.info("FTP连接成功");
        return flag;
    }

    */
/**
     * @param localFile 本地源文件路径
     * @Description:
     *//*

    public boolean uploadFile(String localFile) {
        localFile = localFile.replaceAll("\\\\", "/");
        // 取出文件名
        String fileName = localFile.substring(localFile.lastIndexOf("/") + 1);
        boolean flag = false;
        File file = new File(localFile);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(file);
            String dirname = isDirExist(localFile);
            String remote = new String((dirname + "/" + fileName).getBytes(localCharset), serverCharset);
            out = ftpClient.storeFileStream(remote);
            byte[] buff = new byte[1024 * 8]; // 设定每次传输的数据块大小为8KB
            int read;
            do {
                read = in.read(buff, 0, buff.length);
                if (read > 0) {
                    out.write(buff, 0, read);
                }
                out.flush();
            } while (read >= 0);
            */
/*if(ftpClient.storeFile(remote, in)){
                flag = true;
                logger.info(file.getAbsolutePath()+"上传文件成功！");
            }else{
                logger.error(file.getAbsolutePath()+"上传文件失败！");
            }*//*


        } catch (IOException e) {
            logger.error("上传文件失败", e.getMessage());
        } finally {
            try {
                out.close();
                in.close();
                ftpClient.completePendingCommand();
            } catch (IOException e) {
                logger.error("关闭流失败", e.getMessage());
            }
        }
        return flag;
    }

    */
/**
     * @Description: 判断上传路径是否存在
     *//*

    public String isDirExist(String remoteDir) {
        String[] items = remoteDir.split("/");
        String strPrev = "";
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                if (items[i].length() > 0 && (i != (items.length - 1)) && i != 0) {
                    String dirname = "";
                    if (strPrev.length() > 0) {
                        dirname = strPrev + "/" + items[i];
                    } else {
                        int pos = remoteDir.indexOf("/");
                        if (pos == 0)
                            dirname = "/" + items[i];
                        else
                            dirname = items[i];
                    }


                    createDir(dirname);
                    strPrev = dirname;
                }
            }
        }
        return strPrev;

    }

    */
/**
     * @param basePath upload基础路径
     * @Description: 判断基础上传路径是否存在
     *//*

    public String isBaseExist(String basePath) {
        String[] items = basePath.split("/");
        String strPrev = "";
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                if (items[i].length() > 0) {
                    String dirname = "";
                    if (strPrev.length() > 0) {
                        dirname = strPrev + "/" + items[i];
                    } else {
                        int pos = basePath.indexOf("/");
                        if (pos == 0)
                            dirname = "/" + items[i];
                        else
                            dirname = items[i];
                    }


                    createDir(dirname);
                    strPrev = dirname;
                }
            }
        }
        return strPrev;

    }

    public boolean createDir(String dirname) {
        boolean isCreateSuccess = false;
        try {
            if (!dirname.equals("")) {
                dirname = new String(dirname.getBytes(localCharset), serverCharset);
                if (ftpClient.makeDirectory(dirname)) {
                    isCreateSuccess = true;
                    logger.info("create new directory in base work directory success");
                } else {

                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isCreateSuccess;
    }

    public void disconnect() {
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            logger.error("FTP断开连接失败", e.getMessage());
        }
    }


}
*/
