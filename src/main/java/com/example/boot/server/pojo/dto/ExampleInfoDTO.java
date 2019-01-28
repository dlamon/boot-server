package com.example.boot.server.pojo.dto;

import com.example.boot.server.util.SerializeUtil.Date2LongSerializer;
import com.example.boot.server.util.SerializeUtil.SexSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExampleInfoDTO {
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 性别 1-男 2-女
     */
    @JsonSerialize(using = SexSerializer.class)
    private String sex;

    /**
     * 住址
     */
    private String address;

    /**
     * 薪水
     */
    private BigDecimal salary;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}
