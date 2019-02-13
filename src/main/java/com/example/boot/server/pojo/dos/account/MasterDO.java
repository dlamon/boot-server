package com.example.boot.server.pojo.dos.account;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Data
@Table(name = "`master`")
public class MasterDO {
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 账户编号
     */
    @Column(name = "`acct_no`")
    private String acctNo;

    /**
     * 账户状态 0-正常 1-异常
     */
    @Column(name = "`acct_status`")
    private Short acctStatus;

    /**
     * 客户编号
     */
    @Column(name = "`client_no`")
    private String clientNo;

    /**
     * 账户余额
     */
    @Column(name = "`balance`")
    private BigDecimal balance;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;
}