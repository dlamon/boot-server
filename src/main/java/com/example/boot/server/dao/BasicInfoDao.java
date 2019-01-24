package com.example.boot.server.dao;

import com.example.boot.server.pojo.ddo.BasicInfoDO;
import com.example.boot.server.util.MybatisMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInfoDao extends MybatisMapper<BasicInfoDO> {
}