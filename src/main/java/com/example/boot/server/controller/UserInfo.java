package com.example.boot.server.controller;

import com.example.boot.server.pojo.dos.example.ExampleInfoDO;
import com.example.boot.server.pojo.dos.test.TestInfoDO;
import com.example.boot.server.pojo.dto.ExampleInfoDTO;
import com.example.boot.server.pojo.dto.TestInfoDTO;
import com.example.boot.server.pojo.dto.UserInfoDTO;
import com.example.boot.server.pojo.vo.ResultVO;
import com.example.boot.server.service.ExampleInfoService;
import com.example.boot.server.service.TestInfoService;
import com.example.boot.server.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoWei
 */
@Slf4j
@RestController
public class UserInfo {
    final private ExampleInfoService exampleInfoService;
    final private TestInfoService testInfoService;

    public UserInfo(ExampleInfoService exampleInfoService, TestInfoService testInfoService ) {
        this.exampleInfoService = exampleInfoService;
        this.testInfoService = testInfoService;
    }

    @RequestMapping("/user/info")
    public ResultVO<UserInfoDTO> getUserInfo() {
        /*
        String errorInfo = MessageUtil.getMessage("E000001");
        log.error("[ERROR]errorInfo:{}", errorInfo);

        String errorInfo2 = MessageUtil.getMessage("E000002", new Object[]{"测试参数"});
        log.error("[ERROR]errorInfo2:{}", errorInfo2);
        */

        ExampleInfoDO exampleInfoDO = exampleInfoService.queryExampleInfoById(1);
        log.debug("exampleInfoDO:{}", exampleInfoDO);

        TestInfoDO testInfoDO = testInfoService.queryTestInfoById(1);
        log.debug("testInfoDo:{}", testInfoDO);

        /*
         * 原则上不允许直接修改DO类文件（包括增加注解）
         * 如果不需要进行值转换，可以直接使用DO组织返回结构
         * 如果需要进行值转换，则必须新建DTO进行转换，如下
         */

        ExampleInfoDTO exampleInfoDTO = new ExampleInfoDTO();
        BeanUtils.copyProperties(exampleInfoDO, exampleInfoDTO);

        TestInfoDTO testInfoDTO = new TestInfoDTO();
        BeanUtils.copyProperties(testInfoDO, testInfoDTO);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setExampleInfoDTO(exampleInfoDTO);
        userInfoDTO.setTestInfoDTO(testInfoDTO);
        userInfoDTO.setTestMsg("This is a json ignore message!");

        return ResultUtil.success(userInfoDTO);
    }

}
