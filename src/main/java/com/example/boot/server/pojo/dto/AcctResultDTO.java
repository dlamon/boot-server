package com.example.boot.server.pojo.dto;

import com.example.boot.server.pojo.dos.account.DetailDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author LiaoWei
 */
@Data
public class AcctResultDTO {
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

    /** 总数 */
    private Long total;

    /** 账户变更详情 */
    List<DetailDO> acctDetailList;
}
