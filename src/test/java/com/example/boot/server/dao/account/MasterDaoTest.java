package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.MasterDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
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
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional
@Slf4j
public class MasterDaoTest {

    @Autowired
    MasterDao masterDao;

    @Test
    public void insert() {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331111");
        masterDO.setAcctStatus(Short.valueOf("0"));
        masterDO.setClientNo("19000001");
        masterDO.setBalance(new BigDecimal("333.33"));
        int result = masterDao.insert(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertSelective() {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331112");
        masterDO.setAcctStatus(Short.valueOf("1"));
        masterDO.setClientNo("19000002");
        masterDO.setBalance(new BigDecimal("333.33"));
        int result = masterDao.insert(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        this.insert();
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331111");
        List<MasterDO> masterDOList = masterDao.select(masterDO);
        Assert.assertEquals(1, masterDOList.size());
        masterDO = masterDOList.get(0);
        masterDO.setAcctStatus(Short.valueOf("1"));
        int result = masterDao.updateByPrimaryKey(masterDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectOne() {
        this.insert();
        this.insertSelective();
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671133331111");
        MasterDO result = masterDao.selectOne(masterDO);
        Assert.assertNotNull(result);
    }

    @Test
    public void selectByRowBounds() {
        for (int i = 0; i < 15; i++) {
            this.insert();
        }

        List<MasterDO> masterDOList = masterDao.selectByRowBounds(null, new RowBounds(0, 10));
        Assert.assertNotNull(masterDOList);
        log.debug("RowBounds: 0-10");
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));

        masterDOList = masterDao.selectByRowBounds(null, new RowBounds(10, 10));
        Assert.assertNotNull(masterDOList);
        log.debug("RowBounds: 11-20");
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));
    }
}