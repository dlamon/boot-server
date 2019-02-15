package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.MasterDO;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/02/15
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Repository
public interface MasterDao {
    /**
    * This method corresponds to the database table detail
    */
    int insert(MasterDO record);

    /**
    * This method corresponds to the database table detail
    */
    int insertSelective(MasterDO record);

    /**
    * This method corresponds to the database table detail
    */
    MasterDO selectByPrimaryKey(String acctNo);

    /**
    * This method corresponds to the database table detail
    */
    int updateByPrimaryKeySelective(MasterDO record);

    /**
    * This method corresponds to the database table detail
    */
    int updateByPrimaryKey(MasterDO record);
}