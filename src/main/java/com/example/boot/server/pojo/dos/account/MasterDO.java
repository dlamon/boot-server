package com.example.boot.server.pojo.dos.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019/02/27
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "主要信息")
public class MasterDO {
    /** 账户编号 */
    @ApiModelProperty("账户编号")
    private String acctNo;

    /** 账户状态 0-正常 1-异常 */
    @ApiModelProperty("账户状态 0-正常 1-异常")
    private Short acctStatus;

    /** 客户编号 */
    @ApiModelProperty("客户编号")
    private String clientNo;

    /** 账户余额 */
    @ApiModelProperty("账户余额")
    private BigDecimal balance;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updateTime;
}