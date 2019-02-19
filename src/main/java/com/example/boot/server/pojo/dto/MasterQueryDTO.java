package com.example.boot.server.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Data
public class MasterQueryDTO {
    /** 账户编号 */
    private String acctNo;

    /** 账户状态 0-正常 1-异常 */
    private Short acctStatus;

    /** 客户编号 */
    private String clientNo;

    /** 账户余额 */
    private BigDecimal balance;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 排序字段 */
    private String sort;

    /** 排序方式 */
    private String order;

    /** 分页偏移量 */
    private Integer pageNum;

    /** 单页最大条数 */
    private Integer pageSize;
}
