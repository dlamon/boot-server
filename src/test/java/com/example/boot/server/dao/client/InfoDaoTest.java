package com.example.boot.server.dao.client;

import com.example.boot.server.pojo.dos.client.InfoDO;
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional("clientDataSourceTransactionManager")
@Slf4j
public class InfoDaoTest {

    @Autowired
    InfoDao infoDao;

    @Test
    public void insert() {
        InfoDO infoDO = new InfoDO();
        infoDO.setClientNo("19000001");
        infoDO.setIdNo("500234198809991234");
        int result = infoDao.insert(infoDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertSelective() {
        InfoDO infoDO = new InfoDO();
        infoDO.setClientNo("19000002");
        infoDO.setIdNo("500234198809992345");
        int result = infoDao.insertSelective(infoDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByPrimaryKey() {
        this.insert();
        InfoDO infoDO = infoDao.selectByPrimaryKey("19000001");
        infoDO.setSex("1");
        LocalDate localDate = LocalDate.of(2015, 10, 17);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        infoDO.setBirthDate(Date.from(instant));
        int result = infoDao.updateByPrimaryKey(infoDO);
        Assert.assertEquals(1, result);

    }
    @Test
    public void updateByPrimaryKeySelective() {
        this.insert();
        InfoDO infoDO = new InfoDO();
        infoDO.setClientNo("19000001");
        infoDO.setSex("2");
        int result = infoDao.updateByPrimaryKeySelective(infoDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByPrimaryKey() {
        this.insert();
        InfoDO infoDO = infoDao.selectByPrimaryKey("19000001");
        Assert.assertNotNull(infoDO);
    }
}