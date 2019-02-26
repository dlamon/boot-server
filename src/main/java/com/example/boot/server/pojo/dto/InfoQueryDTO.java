package com.example.boot.server.pojo.dto;

import lombok.Data;

/**
 * @author LiaoWei
 */
@Data
public class InfoQueryDTO {
    /** 客户编号 */
    private String clientNo;

    /** 身份证号 */
    private String idNo;

    /** 排序字段 */
    private String sort;

    /** 排序方式 */
    private String order;

    /** 分页偏移量 */
    private Integer pageNum;

    /** 单页最大条数 */
    private Integer pageSize;
}
