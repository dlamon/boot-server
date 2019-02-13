package com.example.boot.server.pojo.dos.client;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Data
@Table(name = "`info`")
public class InfoDO {
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 客户编号
     */
    @Column(name = "`client_no`")
    private String clientNo;

    /**
     * 身份证号
     */
    @Column(name = "`id_no`")
    private String idNo;

    /**
     * 出生日期
     */
    @Column(name = "`birth_date`")
    private Date birthDate;

    /**
     * 性别 1-男 2-女
     */
    @Column(name = "`sex`")
    private String sex;

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