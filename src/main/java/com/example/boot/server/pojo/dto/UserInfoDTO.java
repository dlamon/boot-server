package com.example.boot.server.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author LiaoWei
 */
@Data
public class UserInfoDTO {
    @JsonProperty("example")
    ExampleInfoDTO exampleInfoDTO;

    @JsonProperty("test")
    TestInfoDTO testInfoDTO;

    @JsonIgnore
    String testMsg;
}
