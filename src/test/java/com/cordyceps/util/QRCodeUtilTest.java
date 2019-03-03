package com.cordyceps.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class QRCodeUtilTest {

    @Test
    public void getQRcodePath() {
        String path = QRCodeUtil.getQRcodePath("nmsl");
        System.out.println(path);
    }

    @Test
    public void qrDecode(){
        String path = QRCodeUtil.getQRcodePath("nmsl");
        String result = QRCodeUtil.qrDecode(path);
        System.out.println(result);
    }
}