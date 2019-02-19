package com.example.boot.server.dao.client;

import com.example.boot.server.pojo.dos.client.InfoDO;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/02/19
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Repository
public interface InfoDao {
    /**
    * This method corresponds to the database table detail
    */
    int insert(InfoDO record);

    /**
    * This method corresponds to the database table detail
    */
    int insertSelective(InfoDO record);

    /**
    * This method corresponds to the database table detail
    */
    InfoDO selectByPrimaryKey(String clientNo);

    /**
    * This method corresponds to the database table detail
    */
    int updateByPrimaryKeySelective(InfoDO record);

    /**
    * This method corresponds to the database table detail
    */
    int updateByPrimaryKey(InfoDO record);
}