package com.example.boot.server.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiaoWei
 */
@Getter
@Setter
@ToString
public class ResultVO<T> {
    private String code;
    private String message;
    private Long total;
    private T data;
}
