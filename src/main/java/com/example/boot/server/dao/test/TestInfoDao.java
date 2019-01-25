package com.example.boot.server.dao.test;

import com.example.boot.server.pojo.ddo.test.TestInfoDO;
import com.example.boot.server.util.MybatisMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInfoDao extends MybatisMapper<TestInfoDO> {
}