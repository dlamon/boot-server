package com.example.boot.server.db.dao;

import com.example.boot.server.db.entity.BasicInfo;

public interface BasicInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasicInfo record);

    int insertSelective(BasicInfo record);

    BasicInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicInfo record);

    int updateByPrimaryKey(BasicInfo record);
}