package com.example.boot.server.controller;

import com.example.boot.server.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWorld {
    @Value("${com.example.env}")
    private String env;
    private Config config;

    public HelloWorld(Config config) {
        this.config = config;
    }

    @RequestMapping("/")
    public String helloWorld() {
        log.info("current env: {}", env);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(env);
        sb.append("] ");
        sb.append(config.getHello());
        sb.append(" ");
        sb.append(config.getWorld());

        return sb.toString();
    }
}
