package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.DetailDO;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/02/15
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Repository
public interface DetailDao {
    /**
    * This method corresponds to the database table detail
    */
    int insert(DetailDO record);

    /**
    * This method corresponds to the database table detail
    */
    int insertSelective(DetailDO record);

    /**
    * This method corresponds to the database table detail
    */
    DetailDO selectByPrimaryKey(String acctNo);

    /**
    * This method corresponds to the database table detail
    */
    int updateByPrimaryKeySelective(DetailDO record);

    /**
    * This method corresponds to the database table detail
    */
    int updateByPrimaryKey(DetailDO record);
}