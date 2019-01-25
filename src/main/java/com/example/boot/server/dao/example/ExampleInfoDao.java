package com.example.boot.server.dao.example;

import com.example.boot.server.pojo.ddo.example.ExampleInfoDO;
import com.example.boot.server.util.MybatisMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleInfoDao extends MybatisMapper<ExampleInfoDO> {
}