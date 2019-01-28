package com.example.boot.server.pojo.ddo.example;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "example_info")
public class ExampleInfoDO {
    /**
     * 编号
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
     * 性别 1-男 2-女
     */
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
    private Date updateTime;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
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
     * 获取性别 1-男 2-女
     *
     * @return sex - 性别 1-男 2-女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别 1-男 2-女
     *
     * @param sex 性别 1-男 2-女
     */
    public void setSex(String sex) {
        this.sex = sex;
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

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}