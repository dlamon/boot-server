package com.example.bootserver.controller;

import com.example.bootserver.config.Config;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@RestController
@Slf4j
@Data
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
    public String helloWorld(@NonNull String name) {
        // return "[" + env + "]" + hello + "," + world;
        log.debug("This is a debug message!");
        return config.getHello() + "," + config.getWorld();
    }
}
