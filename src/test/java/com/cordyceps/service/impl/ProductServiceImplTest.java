package com.cordyceps.service.impl;

import com.cordyceps.dao.ProductMapper;
import com.cordyceps.pojo.Product;
import com.cordyceps.service.IProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductServiceImplTest {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void saveOrUpdateProduct() {
        Product product = productMapper.selectByPrimaryKey(73);
        product.setCategoryId(3);
        product.setLength(new BigDecimal(2));
        product.setWeight(new BigDecimal(2));
        product.setDiggerId(3);
        product.setPrice(new BigDecimal(100));
        iProductService.saveOrUpdateProduct(product);
        System.out.println(product.getId());
    }
}