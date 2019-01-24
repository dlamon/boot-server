package com.example.boot.server.controller;

import com.example.boot.server.config.Config;
import com.example.boot.server.model.BasicInfo;
import com.example.boot.server.service.BasicInfoService;
import com.example.boot.server.util.Message;
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
    private Message message;

    public HelloWorld(Config config, BasicInfoService basicInfoService, Message message) {
        this.config = config;
        this.basicInfoService = basicInfoService;
        this.message = message;
    }

    @RequestMapping("/")
    public String helloWorld() {
        log.debug("[DEBUG]This is a debug message!");
        log.info("[INFO]This is a info message!");

        String errorInfo = message.getMessage("E000001");
        log.error("[ERROR]errorInfo:{}", errorInfo);

        String errorInfo2 = message.getMessage("E000002", new Object[]{"测试参数"});
        log.error("[ERROR]errorInfo2:{}", errorInfo2);

        BasicInfo basicInfo = basicInfoService.queryBasicInfoById(1);

        return config.getEnv() + "," + config.getHello() + "," + config.getWorld() + "," + basicInfo.getName();
    }

}
