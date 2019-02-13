package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.DetailDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author LiaoWei
 */
@Repository
public interface DetailDao extends Mapper<DetailDO> {
}