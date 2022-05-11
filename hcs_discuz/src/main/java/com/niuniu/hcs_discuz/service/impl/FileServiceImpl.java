package com.niuniu.hcs_discuz.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.niuniu.hcs_discuz.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author niuniu
 * @date 2022/3/24
 */
@Service
public class FileServiceImpl implements IFileService {

    @Value("${my.file.serverPath}")
    String serverPath;

    @Value("${my.oss.endpoint}")
    String endpoint;
    @Value("${my.oss.keyid}")
    String keyid;
    @Value("${my.oss.keysecret}")
    String keysecret;
    @Value("${my.oss.bucketname}")
    String bucketname;

//    @Override
//    public String saveUploadFile(MultipartFile file, String dir) {
//        /**
//         * 1、path
//         * filePath = "D:/var/upload/"
//         * path = filePath dir datePath
//         */
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        String datePath = dateFormat.format(new Date());    //拼接日期路径 2022/03/29
//        String resourcePath = dir +"/" + datePath;  //除去服务器路径的路径 avatar/2022/03/29
//        File folderPath = new File(serverPath + resourcePath);
//        if (!folderPath.exists()) folderPath.mkdirs();
//        /**
//         * 2、name
//         */
//        String originalName = file.getOriginalFilename();   //原始文件名
//        String uuid = UUID.randomUUID().toString().replace("-","").substring(0,10); //唯一字符串
//        if (originalName.length()>10)
//            originalName = originalName.substring(originalName.length()-10);
//        String fileName = uuid + "_" + originalName;    //最终文件名
//        /**
//         * 3、存储文件
//         */
//        File dest = new File(folderPath, fileName);
//        try {
//            file.transferTo(dest);
//            return resourcePath + "/" + fileName;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public String saveUploadFile(MultipartFile file) {
        /**
         * 1、path
         * filePath = "D:/var/upload/"
         * path = filePath dir datePath
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String datePath = dateFormat.format(new Date());    //拼接日期路径 2022/03/29
        /**
         * 2、name
         */
        String originalName = file.getOriginalFilename();   //原始文件名
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 10); //唯一字符串
        if (originalName.length() > 10)
            originalName = originalName.substring(originalName.length() - 10);
        String fileName = uuid + "_" + originalName;    //最终文件名
        /**
         * 3、使用Oss存储文件
         */
        String filepath = datePath + "/" + fileName;
        if (this.ossUpload(file, filepath)) {
            return "https://" + bucketname + "." + endpoint + "/" + filepath;
        } else {
            return null;
        }
    }

    private Boolean ossUpload(MultipartFile file, String filepath) {
        OSS ossClient = null;
        try {
            //创建实例
            ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
            //存储
            ossClient.putObject(bucketname, filepath, file.getInputStream());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ossClient.shutdown();
        }
    }

}
