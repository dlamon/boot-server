package com.example.boot.server.service;

import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dto.AcctQueryDTO;
import com.example.boot.server.pojo.dto.AcctResultDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LiaoWei
 */
public interface AcctService {
    /**
     * 查询账户主要信息
     * @param acctNo 账户编号
     * @return 账户主要信息
     */
    MasterDO getMasterInfo(String acctNo);

    /**
     * 查询账户详细信息
     * @param acctNo 账户编号
     * @return 账户详细信息
     */
    List<DetailDO> listDetailInfo(String acctNo);

    /**
     * 新增账户
     * @param masterDO 新增的账户信息
     * @return 账户编号
     */
    String saveAccount(MasterDO masterDO);

    /**
     * 更改账户状态
     * @param acctNo 账户编号
     * @param acctStatus 账户状态 0-正常 1-异常
     */
    void updateStatus(String acctNo, Short acctStatus);

    /**
     * 更改账户关联的客户号
     * @param acctNo 账户编号
     * @param clientNo 客户号
     */
    void updateClientId(String acctNo, String clientNo);

    /**
     * 更改账户备注
     * @param acctNo 账户编号
     * @param remark 备注
     */
    void updateRemark(String acctNo, String remark);

    /**
     * 存款
     * @param acctNo 账户编号
     * @param amount 存款金额
     */
    void deposit(String acctNo, BigDecimal amount);

    /**
     * 取款
     * @param acctNo 账户编号
     * @param amount 取款金额
     * @param use 用途
     */
    void withdrawal(String acctNo, BigDecimal amount, String use);

    /**
     * 通过客户编号查询账号
     * @param clientNo 客户编号
     * @return 账号主要信息列表
     */
    List<MasterDO> listMasterByClientNo(String clientNo);

    /**
     * 综合查询
     * @param acctQueryDTO 综合查询结构
     * @return 综合查询结果
     */
    AcctResultDTO listComplex(AcctQueryDTO acctQueryDTO);

    /**
     * 删除账户信息
     * @param acctNo 账户编号
     */
    void removeAccount(String acctNo);
}
