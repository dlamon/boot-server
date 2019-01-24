package com.example.boot.server.controller;

import com.example.boot.server.config.Config;
import com.example.boot.server.pojo.ddo.BasicInfoDO;
import com.example.boot.server.service.BasicInfoService;
import com.example.boot.server.util.MessageUtil;
import com.example.boot.server.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWorld {

    /*
    @Value("${com.example.env}")
    private String env;
    @Value("${com.example.hello}")
    private String hello;
    @Value("${com.example.world}")
    private String world;
    */

    private Config config;
    private BasicInfoService basicInfoService;

    public HelloWorld(Config config, BasicInfoService basicInfoService) {
        this.config = config;
        this.basicInfoService = basicInfoService;
    }

    @RequestMapping("/")
    public Result helloWorld() {
        log.debug("[DEBUG]This is a debug message!");
        log.info("[INFO]This is a info message!");

        String errorInfo = MessageUtil.getMessage("E000001");
        log.error("[ERROR]errorInfo:{}", errorInfo);

        String errorInfo2 = MessageUtil.getMessage("E000002", new Object[]{"测试参数"});
        log.error("[ERROR]errorInfo2:{}", errorInfo2);

        BasicInfoDO basicInfoDO = basicInfoService.queryBasicInfoById(1);
        log.info("basicInfoDO:{}", basicInfoDO);

        // return config.getEnv() + "," + config.getHello() + "," + config.getWorld() + "," + basicInfo.getName();
        return Result.success(basicInfoDO);
    }

}
