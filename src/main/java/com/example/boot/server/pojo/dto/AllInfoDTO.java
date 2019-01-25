package com.example.boot.server.pojo.dto;

import com.example.boot.server.pojo.ddo.example.ExampleInfoDO;
import com.example.boot.server.pojo.ddo.test.TestInfoDO;
import lombok.Data;

@Data
public class AllInfoDTO {
    ExampleInfoDO exampleInfoDO;
    TestInfoDO testInfoDO;
}
