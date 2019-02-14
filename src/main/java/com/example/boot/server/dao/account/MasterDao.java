package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.MasterDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author LiaoWei
 */
@Repository
public interface MasterDao extends Mapper<MasterDO> {
    public List<MasterDO> selectAllByConditions();
}