package com.example.boot.server.controller;

import com.example.boot.server.config.CustomConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Api(tags = "/", description = "欢迎信息")
public class HelloController {
    @Value("${com.example.env}")
    private String env;
    private final CustomConfig customConfig;

    public HelloController(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @GetMapping("/")
    @ApiOperation("欢迎信息")
    public String hello() {
        log.info("current env: {}", env);
        String hello = customConfig.getHello();
        String world = customConfig.getWorld();
        return "[" + env + "] " + hello + " " + world;
    }
}
