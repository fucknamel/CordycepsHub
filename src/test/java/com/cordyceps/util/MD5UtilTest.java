package com.cordyceps.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilTest {

    @Test
    public void MD5EncodeUtf8(){
        System.out.println(MD5Util.MD5EncodeUtf8("123456"));
    }
}