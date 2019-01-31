package com.cordyceps.dao;

import com.cordyceps.pojo.Transport;

public interface TransportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transport record);

    int insertSelective(Transport record);

    Transport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transport record);

    int updateByPrimaryKey(Transport record);
}