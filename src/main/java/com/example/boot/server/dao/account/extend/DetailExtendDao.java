package com.example.boot.server.dao.account.extend;

import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dto.DetailQueryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiaoWei
 */
@Repository
public interface DetailExtendDao {
    /**
     * 通过多条件组合查询账户主要信息
     * @param detailQueryDTO 查询账户条件
     * @return 查询结果列表
     */
    List<DetailDO> selectAllByConditions(DetailQueryDTO detailQueryDTO);

    /**
     * 通过账户编号删除详细信息
     * @param acctNo 账户编号
     */
    void deleteByPrimaryKey(String acctNo);
}
