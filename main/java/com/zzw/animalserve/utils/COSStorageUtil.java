package com.zzw.animalserve.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/9/6__16:18
 */
public class COSStorageUtil {
    // 1 初始化用户身份信息（secretId, secretKey）。
    //  可通过个人中心- - -访问管理- - -访问密匙- - -API密匙管理查看自己的id，key
    static String SECRET_ID = "************************************";
    static String SECRET_KEY = "***********************************";
    static String BUCKET_NAME = "***************";
    // 对应服务器所在的区域，我的在shanghai
    static String REGION = "**************";


    private COSStorageUtil(){}

    /**
     * 获取客户端对象
     * @return
     */
    public static COSClient getInstance(){
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(REGION);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    // 可上传指定路径(path)的图片到桶中
    public static String localUpFile(String path){
        File localFile = new File(path);
        String key = getTimeStamp()+".jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, localFile);
        PutObjectResult putObjectResult = getInstance().putObject(putObjectRequest);
        return "http://"+BUCKET_NAME+".cos."+REGION+".myqcloud.com/"+key;
    }

    // 本次图片上传使用的方法
    public static String upLoad(InputStream in, String ext, String contentType){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度为500
//        objectMetadata.setContentLength(500);
        // 设置 Content type, 默认是 application/octet-stream
        System.out.println("开始上传到云");
        String key = getTimeStamp()+ext;
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, in,objectMetadata);
        objectMetadata.setContentType(contentType);
        PutObjectResult putObjectResult = getInstance().putObject(BUCKET_NAME, key, in, objectMetadata);
        return "http://"+BUCKET_NAME+".cos."+REGION+".myqcloud.com/"+key;
    }
    // 获得时间戳
    public static String getTimeStamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        return dateTime;
    }

}
