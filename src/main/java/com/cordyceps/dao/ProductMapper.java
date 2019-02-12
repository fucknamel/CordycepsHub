package com.cordyceps.dao;

import com.cordyceps.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();

    List<Product> selectListByTitleAndProductId(@Param("productTitle") String productTitle, @Param("productId") Integer productId);

    List<Product> selectListByTitleAndCategoryIds(@Param("productTitle") String productTitle,@Param("categoryIdList") List<Integer> categoryIdList);
}