package com.example.boot.server.dao.client;

import com.example.boot.server.pojo.dos.client.InfoDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author LiaoWei
 */
@Repository
public interface InfoDao extends Mapper<InfoDO> {
}