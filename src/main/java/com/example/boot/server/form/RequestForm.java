package com.example.boot.server.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author LiaoWei
 */
@Data
public class RequestForm {
    @NotBlank(message = "账号不能为空")
    private String acctNo;
}
