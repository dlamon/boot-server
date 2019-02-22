package com.example.boot.server.pojo.dto;

import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.serializer.SexSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author LiaoWei
 */
@Data
public class ComplexResultDTO {
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
    private Date acctCreateTime;

    /** 修改时间 */
    private Date acctUpdateTime;

    /** 身份证号 */
    private String idNo;

    /** 出生日期 */
    private Date birthDate;

    /** 性别 1-男 2-女 */
    @JsonSerialize(using = SexSerializer.class)
    private String sex;

    /** 创建时间 */
    private Date clientCreateTime;

    /** 修改时间 */
    private Date clientUpdateTime;

    /** 总条数 */
    private long total;

    /** 账户变更详情 */
    @JsonProperty("acctDetails")
    private List<DetailDO> acctDetailList;
}
