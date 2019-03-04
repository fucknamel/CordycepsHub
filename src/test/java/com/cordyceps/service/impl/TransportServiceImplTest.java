package com.cordyceps.service.impl;

import com.cordyceps.service.ITransportService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransportServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private ITransportService iTransportService;

    @Test
    public void updateTransport() {
        try{
            BigDecimal test = new BigDecimal("hello");
            System.out.println("成了" + ": " + test);
        }catch (NumberFormatException e){
            logger.error("经纬度格式错误", e);
        }
    }

    @Test
    public void getQRcodeById(){
        String path = (String) iTransportService.getQRcodeById(1).getData();
        System.out.println(path);
    }
}