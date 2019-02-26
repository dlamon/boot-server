package com.example.boot.server.pojo.dos.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* Created by Mybatis Generator on 2019/02/25
* It's automatically generated, do not modify
* @author LiaoWei
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoDO {
    /** 客户编号 */
    private String clientNo;

    /** 身份证号 */
    private String idNo;

    /** 姓名 */
    private String name;

    /** 性别 1-男 2-女 */
    private String sex;

    /** 出生日期 */
    private Date birthDate;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;
}