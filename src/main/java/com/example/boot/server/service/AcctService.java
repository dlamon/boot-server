package com.example.boot.server.service;

import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dto.AcctQueryDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LiaoWei
 */
public interface AcctService {
    /**
     * 查询账户主要信息
     */
    MasterDO getMasterInfo(String acctNo);

    /**
     * 查询账户详细信息
     */
    List<DetailDO> listDetailInfo(String acctNo);

    /**
     * 新增账户
     */
    void saveAccount(MasterDO masterDO);

    /**
     * 更改账户状态
     */
    void updateStatus(String acctNo, Short acctStatus);

    /**
     * 更改账户关联的客户号
     */
    void updateClientId(String acctNo, String clientNo);

    /**
     * 更改账户备注
     */
    void updateRemark(String acctNo, String remark);

    /**
     * 存款
     */
    void deposit(String acctNo, BigDecimal amount);

    /**
     * 取款
     */
    void withdrawal(String acctNo, BigDecimal amount, String use);


    /**
     * 综合查询
     */
    AcctQueryDTO queryComplex(AcctQueryDTO acctQueryDTO);
}
