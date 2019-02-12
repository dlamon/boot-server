package com.example.boot.server.pojo.dos.test;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LiaoWei
 */
@Data
@Table(name = "`test_info`")
public class TestInfoDO {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`user`")
    private String user;

    @Column(name = "`result`")
    private String result;
}