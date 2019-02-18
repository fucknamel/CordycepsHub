package com.cordyceps.dao;

import com.cordyceps.pojo.Transport;

import java.util.List;

public interface TransportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transport record);

    int insertSelective(Transport record);

    Transport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transport record);

    int updateByPrimaryKey(Transport record);

    List<Transport> selectListByProductId(Integer productId);
}