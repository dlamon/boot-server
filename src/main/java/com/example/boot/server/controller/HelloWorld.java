package com.example.boot.server.controller;

import com.example.boot.server.config.CustomConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoWei
 */
@Slf4j
@RestController
public class HelloWorld {
    @Value("${com.example.env}")
    private String env;
    private final CustomConfig customConfig;

    public HelloWorld(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @RequestMapping("/")
    public String helloWorld() {
        log.info("current env: {}", env);
        String hello = customConfig.getHello();
        String world = customConfig.getWorld();
        return "[" + env + "] " + hello + " " + world;
    }
}
