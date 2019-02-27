package com.example.boot.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LiaoWei
 */
@Component
@ConfigurationProperties(prefix="com.example")
@Getter
@Setter
public class CustomConfig {
    private String env;
    private String hello;
    private String world;
}
