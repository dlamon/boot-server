package com.example.boot.server.pojo.ddo;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "basic_info")
public class BasicInfoDO {
    /**
     * 自增编号
     */
    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 住址
     */
    private String address;

    /**
     * 薪水
     */
    private BigDecimal salary;

    /**
     * 获取自增编号
     *
     * @return id - 自增编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增编号
     *
     * @param id 自增编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Byte getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * 获取住址
     *
     * @return address - 住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置住址
     *
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取薪水
     *
     * @return salary - 薪水
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * 设置薪水
     *
     * @param salary 薪水
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}