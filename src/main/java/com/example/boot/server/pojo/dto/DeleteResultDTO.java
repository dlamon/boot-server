package com.example.boot.server.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author LiaoWei
 */
@Data
public class DeleteResultDTO {
    /** 删除账户编号列表 */
    @JsonProperty("deletes")
    List<String> deleteList;
}
