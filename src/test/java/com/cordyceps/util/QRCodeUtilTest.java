package com.cordyceps.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class QRCodeUtilTest {

    @Test
    public void getQRcodePath() {
        String path = QRCodeUtil.getQRcodePath("www.baidu.com");
        System.out.println(path);
    }

    @Test
    public void qrDecode(){
        String path = "/usr/local/Cellar/tomcat/9.0.6/libexec/bin/temp/temp.png";
        String result = QRCodeUtil.qrDecode(path);
        System.out.println(result);
    }
}