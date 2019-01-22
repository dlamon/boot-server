package com.example.boot.server.controller;

import com.example.boot.server.config.Config;
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

    public HelloWorld(Config config) {
        this.config = config;
    }

    @RequestMapping("/")
    public String helloWorld(String name) {
        // return "[" + env + "]" + hello + "," + world;
        log.debug("[DEBUG]This is a debug message!");
        log.info("[INFO]This is a debug message!");
        return config.getEnv() + "," + config.getHello() + "," + config.getWorld();
    }
}
