package com.voicecyber.ftp;/*
package com.duplicall.vclog.core.utils.upload;*/
/**
 * Created by limeng on 2018/1/30.
 *//*


import com.vclog.persistence.model.UploadConnection;

*/
/**
 * @author mli & gan
 * @description
 * @create 2018-01-30 16:45
 **//*

public interface IUpload {
    boolean connect(UploadConnection upload);

    */
/**
     * @param localFile 本地源文件路径
     * @Description:
     *//*

    boolean uploadFile(String localFile);

    */
/**
     * @Description: 判断上传路径是否存在
     *//*

    String isDirExist(String remoteDir);

    */
/**
     * @param basePath upload基础路径
     * @Description: 判断基础上传路径是否存在
     *//*

    String isBaseExist(String basePath);

    boolean createDir(String dirname);

    void disconnect();
}
*/
