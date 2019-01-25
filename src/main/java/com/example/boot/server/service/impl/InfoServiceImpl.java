package com.example.boot.server.service.impl;

import com.example.boot.server.pojo.ddo.example.ExampleInfoDO;
import com.example.boot.server.pojo.ddo.test.TestInfoDO;
import com.example.boot.server.pojo.dto.AllInfoDTO;
import com.example.boot.server.service.ExampleInfoService;
import com.example.boot.server.service.InfoService;
import com.example.boot.server.service.TestInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InfoServiceImpl implements InfoService {
    private ExampleInfoService exampleInfoService;
    private TestInfoService testInfoService;

    public InfoServiceImpl(ExampleInfoService exampleInfoService, TestInfoService testInfoService) {
        this.exampleInfoService = exampleInfoService;
        this.testInfoService = testInfoService;
    }

    @Override
    public AllInfoDTO getAllInfoById(Integer id) {
        ExampleInfoDO exampleInfoDO = exampleInfoService.queryExampleInfoById(1);
        log.info("exampleInfoDO:{}", exampleInfoDO);

        TestInfoDO testInfoDO = testInfoService.queryTestInfoById(1);
        log.info("testInfoDo:{}", testInfoDO);

        AllInfoDTO allInfoDTO = new AllInfoDTO();
        allInfoDTO.setExampleInfoDO(exampleInfoDO);
        allInfoDTO.setTestInfoDO(testInfoDO);
        return allInfoDTO;
    }
}
