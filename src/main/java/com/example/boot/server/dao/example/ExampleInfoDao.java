package com.example.boot.server.dao.example;

import com.example.boot.server.pojo.dos.example.ExampleInfoDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author LiaoWei
 */
@Repository
public interface ExampleInfoDao extends Mapper<ExampleInfoDO> {
}