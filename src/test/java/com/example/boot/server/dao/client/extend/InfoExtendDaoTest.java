package com.example.boot.server.dao.client.extend;

import com.example.boot.server.dao.client.InfoDao;
import com.example.boot.server.pojo.dos.client.InfoDO;
import com.example.boot.server.pojo.dto.InfoQueryDTO;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional("clientDataSourceTransactionManager")
@Slf4j
public class InfoExtendDaoTest {
    @Autowired
    InfoDao infoDao;
    @Autowired
    InfoExtendDao infoExtendDao;

    @Before
    public void setUp() {
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 8, 8, 8);
        Date birthDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        InfoDO infoDO = new InfoDO("19000001", "500235199909991990", "杨晶晶", "2", birthDate, null, null);
        infoDao.insert(infoDO);

        localDateTime = LocalDateTime.of(2016, 10, 17, 8, 8, 8);
        birthDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        infoDO = new InfoDO("19000002", "500235200009992000", "莫潘潘", "1", birthDate, null, null);
        infoDao.insert(infoDO);
    }

    @Test
    public void selectAllByConditions() {
        // 查询所有数据
        log.debug("--- Select All by no conditions ---");
        InfoQueryDTO infoQueryDTO = new InfoQueryDTO();
        List<InfoDO> infoDOList = infoExtendDao.selectAllByConditions(infoQueryDTO);
        infoDOList.forEach(element -> log.debug("{}", element));
        Assert.assertTrue(infoDOList.size() > 1);

        // 通过客户编号查询
        log.debug("--- Select All by clientNo ---");
        infoQueryDTO = new InfoQueryDTO();
        infoQueryDTO.setClientNo("19000001");
        infoDOList = infoExtendDao.selectAllByConditions(infoQueryDTO);
        infoDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(1, infoDOList.size());

        // 通过证件编号查询
        log.debug("--- Select All by clientNo ---");
        infoQueryDTO = new InfoQueryDTO();
        infoQueryDTO.setIdNo("500235200009992000");
        infoDOList = infoExtendDao.selectAllByConditions(infoQueryDTO);
        infoDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(1, infoDOList.size());
    }

}