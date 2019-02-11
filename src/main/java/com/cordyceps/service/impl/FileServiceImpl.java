package com.cordyceps.service.impl;

import com.cordyceps.service.IFileService;
import com.cordyceps.util.FTPUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件，上传文件的原文件名:{}，上传的路径:{}，新文件名:{}", fileName, path, uploadFileName);

        //先将文件暂存在tomcat文件夹中
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try {
            //文件已经上传成功了
            file.transferTo(targetFile);

            //之后将文件上传到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            targetFile.delete();
        }catch (IOException e){
            logger.error("文件上传异常", e);
        }

        return targetFile.getName();
    }
}
