package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.DetailDO;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/02/19
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
}