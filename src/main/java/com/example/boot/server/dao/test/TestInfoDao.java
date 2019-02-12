package com.example.boot.server.dao.test;

import com.example.boot.server.pojo.dos.test.TestInfoDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author LiaoWei
 */
@Repository
public interface TestInfoDao extends Mapper<TestInfoDO> {
}