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
@Table(name = "`detail`")
public class DetailDO {
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 账户编号
     */
    @Column(name = "`acct_no`")
    private String acctNo;

    /**
     * 发生金额
     */
    @Column(name = "`amount`")
    private BigDecimal amount;

    /**
     * 账户余额
     */
    @Column(name = "`balance`")
    private BigDecimal balance;

    /**
     * 用途
     */
    @Column(name = "`use`")
    private String use;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;
}