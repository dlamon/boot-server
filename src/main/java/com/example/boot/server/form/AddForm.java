package com.example.boot.server.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Data
@ApiModel(description = "添加表单")
public class AddForm {
    /** 身份证号 */
    @NotBlank(message = "身份证号不能为空")
    @Size(max = 18, min = 18)
    @ApiModelProperty("身份证号码")
    private String idNo;

    /** 姓名 */
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String name;

    /** 性别 1-男 2-女 */
    @ApiModelProperty("性别 1-男 2-女")
    private String sex;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @ApiModelProperty("出生日期")
    private Date birthDate;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;
}
