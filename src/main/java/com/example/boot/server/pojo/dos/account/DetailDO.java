package com.example.boot.server.pojo.dos.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019/02/15
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDO {
    /** 账户编号 */
    private String acctNo;

    /** 发生金额 */
    private BigDecimal amount;

    /** 账户余额 */
    private BigDecimal balance;

    /** 用途 */
    private String uses;

    /** 创建时间 */
    private Date createTime;
}