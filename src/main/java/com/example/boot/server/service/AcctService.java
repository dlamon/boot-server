package com.example.boot.server.service;

import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dto.AcctDTO;
import com.github.pagehelper.PageRowBounds;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiaoWei
 */
public interface AcctService {
    /**
     * 新增账户
     */
    void addAccount(MasterDO masterDO);

    /**
     * 更改账户状态
     */
    void changeStatus(String acctNo, Short acctStatus);

    /**
     * 更改账户关联的客户号
     */
    void changeClientId(String acctNo, String clientNo);

    /**
     * 更改账户备注
     */
    void changeRemark(String acctNo, String remark);

    /**
     * 存款
     */
    void deposit(String acctNo, BigDecimal amount);

    /**
     * 取款
     */
    void withdrawal(String acctNo, BigDecimal amount, String use);

    /**
     * 查询账户明细
     */
    AcctDTO query(String acctNo, Date beginDate, Date endDate, BigDecimal maxAmount, BigDecimal minAmount, PageRowBounds bounds);
}
