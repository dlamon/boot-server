package com.example.boot.server.controller;

import com.example.boot.server.config.Config;
import com.example.boot.server.pojo.dto.AllInfoDTO;
import com.example.boot.server.pojo.vo.ResultVO;
import com.example.boot.server.service.InfoService;
import com.example.boot.server.util.MessageUtil;
import com.example.boot.server.util.ResultUtil;
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
    private InfoService infoService;

    public HelloWorld(Config config, InfoService infoService) {
        this.config = config;
        this.infoService = infoService;
    }

    @RequestMapping("/")
    public ResultVO<AllInfoDTO> helloWorld() {
        log.debug("[DEBUG]This is a debug message!");
        log.info("[INFO]This is a info message!");

        String errorInfo = MessageUtil.getMessage("E000001");
        log.error("[ERROR]errorInfo:{}", errorInfo);

        String errorInfo2 = MessageUtil.getMessage("E000002", new Object[]{"测试参数"});
        log.error("[ERROR]errorInfo2:{}", errorInfo2);

        AllInfoDTO allInfoDTO = infoService.getAllInfoById(1);

        // return config.getEnv() + "," + config.getHello() + "," + config.getWorld() + "," + basicInfo.getName();
        return ResultUtil.success(allInfoDTO);
    }

}
