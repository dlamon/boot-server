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
public class MasterDO {
    /** 账户编号 */
    private String acctNo;

    /** 账户状态 0-正常 1-异常 */
    private Short acctStatus;

    /** 客户编号 */
    private String clientNo;

    /** 账户余额 */
    private BigDecimal balance;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;
}