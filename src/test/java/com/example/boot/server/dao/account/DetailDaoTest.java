package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.DetailDO;
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
public class DetailDaoTest {

    @Autowired
    DetailDao detailDao;

    @Test
    public void insert() {
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo("6228671133331111");
        detailDO.setAmount(new BigDecimal("100.11"));
        detailDO.setBalance(new BigDecimal("200.11"));
        detailDO.setUse("信用卡还款");
        int result = detailDao.insert(detailDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertSelective() {
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo("6228671133331111");
        detailDO.setAmount(new BigDecimal("200.99"));
        detailDO.setBalance(new BigDecimal("300.22"));
        int result = detailDao.insertSelective(detailDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        this.insert();
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo("6228671133331111");
        List<DetailDO> detailDOList = detailDao.select(detailDO);
        Assert.assertEquals(1, detailDOList.size());
        int result = detailDao.updateByPrimaryKeySelective(detailDOList.get(0));
        Assert.assertEquals(1, result);
    }

    @Test
    public void select() {
        this.insert();
        this.insertSelective();
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo("6228671133331111");
        List<DetailDO> result = detailDao.select(detailDO);
        Assert.assertTrue(result.size() > 1);
    }

    @Test
    public void selectByRowBounds() {
        for (int i = 0; i < 15; i++) {
            this.insert();
        }

        List<DetailDO> detailDOList = detailDao.selectByRowBounds(null, new RowBounds(0, 10));
        Assert.assertNotNull(detailDOList);
        log.debug("RowBounds: 0-10");
        detailDOList.forEach(detailDO -> log.debug("{}", detailDO));

        detailDOList = detailDao.selectByRowBounds(null, new RowBounds(10, 10));
        Assert.assertNotNull(detailDOList);
        log.debug("RowBounds: 11-20");
        detailDOList.forEach(detailDO -> log.debug("{}", detailDO));
    }
}