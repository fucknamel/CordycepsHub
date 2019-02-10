package com.cordyceps.service;

import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.Product;
import com.cordyceps.vo.ProductDetailVo;
import com.github.pagehelper.PageInfo;

public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productTitle, Integer productId, int pageNum, int pageSize);
}
