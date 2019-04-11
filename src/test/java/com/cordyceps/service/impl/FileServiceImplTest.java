package com.cordyceps.service.impl;

import com.cordyceps.service.IFileService;
import com.cordyceps.util.QRCodeUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.*;

public class FileServiceImplTest {

    @Autowired
    private IFileService iFileService;

    @Test
    public void uploadLocalFile() {
        String path = QRCodeUtil.getQRcodePath("www.baidu.com");
        File file = new File(path);
        System.out.println(iFileService.uploadLocalFile(file, path));
    }
}