package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.MasterDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional
@Slf4j
public class MasterDaoTest {

    @Autowired
    MasterDao masterDao;

    @Before
    public void setUp() throws Exception {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331111");
        masterDO.setAcctStatus(Short.valueOf("0"));
        masterDO.setClientNo("19000001");
        masterDO.setBalance(new BigDecimal("333.33"));
        int result = masterDao.insert(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insert() {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331112");
        masterDO.setAcctStatus(Short.valueOf("0"));
        masterDO.setClientNo("19000001");
        masterDO.setBalance(new BigDecimal("333.33"));
        int result = masterDao.insert(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertSelective() {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331113");
        masterDO.setAcctStatus(Short.valueOf("1"));
        masterDO.setClientNo("19000002");
        masterDO.setBalance(new BigDecimal("333.33"));
        int result = masterDao.insert(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByPrimaryKey() {
        MasterDO masterDO = masterDao.selectByPrimaryKey("6228671133331111");
        masterDO.setBalance(new BigDecimal("333.35"));
        masterDO.setUpdateTime(new Date());
        int result = masterDao.updateByPrimaryKey(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331111");
        masterDO.setBalance(new BigDecimal("333.36"));
        masterDO.setUpdateTime(new Date());
        int result = masterDao.updateByPrimaryKeySelective(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByPrimaryKey() {
        MasterDO masterDO = masterDao.selectByPrimaryKey("6228671133331111");
        Assert.assertNotNull(masterDO);
    }
}