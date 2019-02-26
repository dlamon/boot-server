package com.example.boot.server.dao.client.extend;

import com.example.boot.server.pojo.dos.client.InfoDO;
import com.example.boot.server.pojo.dto.InfoQueryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiaoWei
 */
@Repository
public interface InfoExtendDao {
    /**
     * 通过多条件组合查询账户主要信息
     * @param infoQueryDTO 查询条件
     * @return 查询结果列表
     */
    List<InfoDO> selectAllByConditions(InfoQueryDTO infoQueryDTO);

    /**
     * 通过客户编号删除客户信息
     * @param clientNo 客户编号
     */
    void deleteByPrimaryKey(String clientNo);
}
