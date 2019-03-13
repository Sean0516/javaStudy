package com.voicecyber.ftp;/*
package com.duplicall.vclog.core.utils.upload;

import com.jcraft.jsch.*;
import com.vclog.persistence.model.UploadConnection;
import org.slf4j.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

*/
/**
 * Created by hu on 2018-01-15.
 *//*

public class SftpUpload implements IUpload {

    private JSch jsch = null;
    private Session session = null;
    private ChannelSftp channelSftp = null;
    private Channel channel = null;
    private String basePath = "";

    private static Logger logger = LoggerFactory.getLogger(SftpUpload.class);



    public boolean connect(UploadConnection upload) {

        String ftpHost = upload.getIp();
        int ftpPort = Integer.parseInt(upload.getPort());
        String ftpUserName = upload.getUserName();
        String ftpPassword = upload.getPassword();
        basePath = upload.getBasePath();


        jsch = new JSch(); // 创建JSch对象
        try {
            session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        } catch (JSchException e) {
            e.printStackTrace();
        }

        if (ftpPassword != null) {
            session.setPassword(ftpPassword); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties

        try {
            session.setTimeout(60000); // 设置timeout时间
            session.connect(); // 通过Session建立链接

            channel = session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
        } catch (JSchException e) {
            e.printStackTrace();
        }
        channelSftp = (ChannelSftp) channel;
        return true;
    }

    */
/**
     * @Description:
     * @param localFile 本地源文件路径
     *//*

    public boolean uploadFile(String localFile){
        isBaseExist(basePath);
        localFile = localFile.replaceAll("\\\\", "/");
        String dst = isDirExist(localFile);
        String fileName = localFile.substring(localFile.lastIndexOf("/") + 1);
        OutputStream out = null; // 使用OVERWRITE模式
        try {
            out = channelSftp.put(dst + "/" + fileName, ChannelSftp.OVERWRITE);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        byte[] buff = new byte[1024 * 8]; // 设定每次传输的数据块大小为8KB
        int read;
        InputStream in = null;
        if (out != null) {

            try {
                in = new FileInputStream(localFile);
                do {
                    read = in.read(buff, 0, buff.length);
                    if (read > 0) {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);

            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else {
            logger.error("上传文件失败");
            return false;
        }
        int chmodInt = Integer.parseInt("777",8);
        try {
            channelSftp.chmod(chmodInt,dst + "/" + fileName);
        } catch (SftpException e) {
            logger.error("chmod " + dst + "/" + fileName + "failed",e.getMessage());
        }
        return true;
    }


    */
/**
     * @Description:
     * 判断上传路径是否存在
     *//*

    public String isDirExist(String remoteDir){
        String [] items = remoteDir.split("/");
        String strPrev = basePath;
        if(items.length > 0){
            for(int i = 0; i < items.length; i++){
                if(items[i].length() > 0 && (i != (items.length - 1)) && i != 0){
                    String dirname = "";
                    if(strPrev.length() > 0){
                        dirname = strPrev + "/" + items[i];
                    }else {
                        int pos = remoteDir.indexOf("/");
                        if(pos == 0)
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
     * @Description:
     * 判断基础上传路径是否存在
     * @param basePath upload基础路径
     *//*

    public String isBaseExist(String basePath){
        String [] items = basePath.split("/");
        String strPrev = "";
        if(items.length > 0){
            for(int i = 0; i < items.length; i++){
                if(items[i].length() > 0){
                    String dirname = "";
                    if(strPrev.length() > 0){
                        dirname = strPrev + "/" + items[i];
                    }else {
                        int pos = basePath.indexOf("/");
                        if(pos == 0)
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
            channelSftp.mkdir(dirname);
            isCreateSuccess = true;
        } catch (SftpException e) {
            */
/*e.printStackTrace();*//*

        }
        return isCreateSuccess;
    }

    public void disconnect() {
        if(channelSftp != null){
            if(channelSftp.isConnected()){
                channelSftp.disconnect();
            }
        }
        if (channel != null) {
            if(channel.isConnected()) {
                channel.disconnect();
            }
        }
        if (session != null) {
            if(session.isConnected()) {
                session.disconnect();
            }
        }
        jsch = null;
    }
}
*/
