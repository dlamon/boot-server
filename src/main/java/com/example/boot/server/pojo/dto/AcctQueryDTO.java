package com.example.boot.server.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Data
public class AcctQueryDTO {
    /** 账户编号 */
    String acctNo;

    /** 开始时间 */
    Date beginDate;

    /** 结束时间 */
    Date endDate;

    /** 最大发生金额 */
    BigDecimal maxAmount;

    /** 最小发生金额 */
    BigDecimal minAmount;

    /** 排序字段 */
    private String sort;

    /** 排序方式 */
    private String order;

    /** 分页偏移量 */
    private Integer pageNum;

    /** 单页最大条数 */
    private Integer pageSize;
}
