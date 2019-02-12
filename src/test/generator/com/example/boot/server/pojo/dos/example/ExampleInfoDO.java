package com.example.boot.server.pojo.dos.example;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`example_info`")
public class ExampleInfoDO {
    /**
     * 编号
     */
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 年龄
     */
    @Column(name = "`age`")
    private Byte age;

    /**
     * 性别 1-男 2-女
     */
    @Column(name = "`sex`")
    private String sex;

    /**
     * 住址
     */
    @Column(name = "`address`")
    private String address;

    /**
     * 薪水
     */
    @Column(name = "`salary`")
    private BigDecimal salary;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;
}