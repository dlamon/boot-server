package com.example.boot.server.dao.account.extend;

import com.example.boot.server.pojo.dos.account.MasterDO;
import com.github.pagehelper.PageRowBounds;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author LiaoWei
 */
@Repository
public interface MasterExtendDao {
    /**
     * 通过客户编号查询账户主要信息
     * @param clientNo 客户编号
     * @return 查询结果列表
     */
    List<MasterDO> selectAllByClientNo(String clientNo);

    /**
     * 通过客户编号分页查询账户主要信息
     * @param clientNo 客户编号
     * @param pageRowBounds 分页结构
     * @return 查询结果列表
     */
    List<MasterDO> selectAllByClientNo(String clientNo, PageRowBounds pageRowBounds);

    /**
     * 通过多条件组合查询账户主要信息
     * @param acctStatus 账户状态
     * @param clientNo 客户编号
     * @param balance 账户余额
     * @param createTime 创建时间
     * @param updateTime 更新时间
     * @return 查询结果列表
     */
    List<MasterDO> selectAllByConditions(Short acctStatus, String clientNo, BigDecimal balance, Date createTime, Date updateTime);
}
