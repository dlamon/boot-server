package com.example.bootserver.controller;

import com.example.bootserver.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @Value("${com.example.env}")
    private String env;
    @Value("${com.example.hello}")
    private String hello;
    @Value("${com.example.world}")
    private String world;

    @Autowired
    private Config config;

    @RequestMapping("/")
    public String helloWorld() {
        return "[" + env + "]" + hello + "," + world;
        // return config.getHello() + "," + config.getWorld();
    }
}
