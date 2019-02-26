package com.example.boot.server.dao.client;

import com.example.boot.server.pojo.dos.client.InfoDO;
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Before
    public void setUp() {
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 8, 8, 8);
        Date birthDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        InfoDO infoDO = new InfoDO("19000001", "500235199909991990", "赵齐齐", "2", birthDate, null, null);
        infoDao.insert(infoDO);

        localDateTime = LocalDateTime.of(2016, 10, 17, 8, 8, 8);
        birthDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        infoDO = new InfoDO("19000002", "500235200009992000", "满依依", "1", birthDate, null, null);
        infoDao.insert(infoDO);
    }

    @Test
    public void insert() {
        InfoDO infoDO = new InfoDO();
        infoDO.setClientNo("19000011");
        infoDO.setIdNo("500234198809991234");
        infoDO.setName("赵齐齐");
        int result = infoDao.insert(infoDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertSelective() {
        InfoDO infoDO = new InfoDO();
        infoDO.setClientNo("19000012");
        infoDO.setIdNo("500234198809992345");
        int result = infoDao.insertSelective(infoDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByPrimaryKey() {
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
        InfoDO infoDO = new InfoDO();
        infoDO.setClientNo("19000001");
        infoDO.setSex("2");
        int result = infoDao.updateByPrimaryKeySelective(infoDO);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByPrimaryKey() {
        InfoDO infoDO = infoDao.selectByPrimaryKey("19000001");
        Assert.assertNotNull(infoDO);
    }
}