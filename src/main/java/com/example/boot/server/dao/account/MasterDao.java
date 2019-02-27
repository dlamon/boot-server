package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.MasterDO;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/02/27
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Repository
public interface MasterDao {
    /**
    * insert
    * @param record table master data object
    * @return affected rows
    */
    int insert(MasterDO record);

    /**
    * insertSelective
    * @param record table master data object
    * @return affected rows
    */
    int insertSelective(MasterDO record);

    /**
    * selectByPrimaryKey
    * @param acctNo primary key
    * @return table master data object
    */
    MasterDO selectByPrimaryKey(String acctNo);

    /**
    * updateByPrimaryKeySelective
    * @param record table master data object
    * @return affected rows
    */
    int updateByPrimaryKeySelective(MasterDO record);

    /**
    * updateByPrimaryKey
    * @param record table master data object
    * @return affected rows
    */
    int updateByPrimaryKey(MasterDO record);
}