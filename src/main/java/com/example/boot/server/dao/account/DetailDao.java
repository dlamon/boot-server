package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.DetailDO;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/02/27
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Repository
public interface DetailDao {
    /**
    * insert
    * @param record table detail data object
    * @return affected rows
    */
    int insert(DetailDO record);

    /**
    * insertSelective
    * @param record table detail data object
    * @return affected rows
    */
    int insertSelective(DetailDO record);
}