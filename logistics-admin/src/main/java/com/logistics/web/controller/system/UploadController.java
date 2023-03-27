package com.logistics.web.controller.system;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.logistics.common.core.domain.AjaxResult;
import com.logistics.common.utils.FileUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system/file")
public class UploadController {
    @PostMapping("/upload")
    public AjaxResult validCanCheckIn(@RequestParam("file") MultipartFile mulFile,
                                      @RequestParam("filePath") String dir,
                                      @RequestParam("fileType") String fileType) throws Exception{
        if (mulFile==null) {
            return AjaxResult.error("没有上传文件");
        }
        System.out.println("fileName:"+mulFile);

        System.out.println("fileType:"+fileType);
        File file = FileUtils.multipartFileToFile(mulFile);
        Map<Object, Object> responseData = new HashMap<>();
        dir = StrUtil.format("{}/{}","images",  dir);


        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "1111";
        String secretKey = "111111";
        String bucket = "ap-shanghai"; //所属区域
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(bucket);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 指定要上传到的存储桶
        String bucketName = "999";
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String key = dir + UUID.randomUUID()+ "." + suffix;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        // 上传文件。
        cosClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        cosClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
        URL url = cosClient.generatePresignedUrl(bucketName, key, expiration);
        String resultStr = "https://" +  url.getHost() + "/" + key;

        responseData.put("link", resultStr);
        System.out.println("link:---"+resultStr);
        return AjaxResult.success().put("link",resultStr);
    }
}
