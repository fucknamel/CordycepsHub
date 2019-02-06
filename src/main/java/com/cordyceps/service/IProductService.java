package com.cordyceps.service;

import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.Product;

public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);
}
