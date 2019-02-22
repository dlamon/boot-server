package com.example.boot.server.controller;

import com.example.boot.server.service.AcctService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
public class ManageController {
    private final AcctService acctService;

    public ManageController(AcctService acctService) {
        this.acctService = acctService;
    }

    @RequestMapping("/manage/add")
    public void addAccount() {

    }
}
