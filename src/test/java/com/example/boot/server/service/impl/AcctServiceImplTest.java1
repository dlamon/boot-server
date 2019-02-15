package com.example.boot.server.service.impl;

import com.example.boot.server.exception.BootException;
import com.example.boot.server.pojo.dos.account.MasterDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional
@Slf4j
public class AcctServiceImplTest {
    @Autowired
    AcctServiceImpl acctService;

    @Test
    public void addAccount() {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671141418888");
        masterDO.setBalance(new BigDecimal("30000"));
        masterDO.setClientNo("1234567890");
        masterDO.setAcctStatus(Short.valueOf("0"));
        acctService.addAccount(masterDO);
    }

    @Test(expected= BootException.class)
    public void addAccountException() {
        this.addAccount();
        this.addAccount();
    }
}