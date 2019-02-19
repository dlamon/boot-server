package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.DetailDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
public class DetailDaoTest {

    @Autowired
    DetailDao detailDao;

    @Test
    public void insert() {
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo("6228671133331111");
        detailDO.setAmount(new BigDecimal("100.11"));
        detailDO.setBalance(new BigDecimal("200.11"));
        detailDO.setUses("信用卡还款");
        int result = detailDao.insert(detailDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertSelective() {
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo("6228671133331112");
        detailDO.setAmount(new BigDecimal("200.99"));
        detailDO.setBalance(new BigDecimal("300.22"));
        int result = detailDao.insertSelective(detailDO);
        Assert.assertEquals(1, result);
    }
}