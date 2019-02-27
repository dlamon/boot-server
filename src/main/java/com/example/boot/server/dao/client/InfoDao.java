package com.example.boot.server.dao.client;

import com.example.boot.server.pojo.dos.client.InfoDO;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/02/27
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Repository
public interface InfoDao {
    /**
    * insert
    * @param record table info data object
    * @return affected rows
    */
    int insert(InfoDO record);

    /**
    * insertSelective
    * @param record table info data object
    * @return affected rows
    */
    int insertSelective(InfoDO record);

    /**
    * selectByPrimaryKey
    * @param clientNo primary key
    * @return table info data object
    */
    InfoDO selectByPrimaryKey(String clientNo);

    /**
    * updateByPrimaryKeySelective
    * @param record table info data object
    * @return affected rows
    */
    int updateByPrimaryKeySelective(InfoDO record);

    /**
    * updateByPrimaryKey
    * @param record table info data object
    * @return affected rows
    */
    int updateByPrimaryKey(InfoDO record);
}