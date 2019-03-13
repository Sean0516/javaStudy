package com.voicecyber.ftp;/*
package com.duplicall.vclog.core.utils.upload;

import com.vclog.persistence.model.UploadConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

*/
/**
 * Created by hu on 2018-01-15.
 *//*

public class CopyUpload implements IUpload {

    private String basePath = "";

    private static Logger logger = LoggerFactory.getLogger(CopyUpload.class);

    */
/**
     * @Description:
     * 获取FTPClient对象
     *//*

    public boolean connect(UploadConnection upload) {
        basePath = upload.getBasePath();
        isBaseExist(basePath);
        return true;
    }

    */
/**
     * @Description:
     * @param localFile 本地源文件路径
     *//*


    public boolean uploadFile(String localFile) {
        boolean flag = false;
        localFile = localFile.replaceAll("\\\\","/");
        // 取出文件名
        String fileName = localFile.substring(localFile.lastIndexOf("/") + 1);
        String dirname = isDirExist(localFile);
        try {
            Files.copy(new File(localFile).toPath(),new File((basePath + "/" + dirname + "/" + fileName)).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error("上传文件失败",e.getMessage());
        }

        return flag;
    }

    */
/**
     * @Description:
     * 判断上传路径是否存在
     *//*

    public String isDirExist(String remoteDir){
        String [] items = remoteDir.split("/");
        String strPrev = "";
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


                    createDir(basePath + "/" +dirname);
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
            Files.createDirectory(new File(dirname).toPath());
            isCreateSuccess = true;
        } catch (IOException e) {

        }

        return isCreateSuccess;
    }

    public void disconnect(){

    }


}
*/
