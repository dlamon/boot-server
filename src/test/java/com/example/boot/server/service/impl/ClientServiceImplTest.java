package com.example.boot.server.service.impl;

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
public class ClientServiceImplTest {
    @Autowired
    ClientServiceImpl clientService;
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
    public void saveClientInfo() {
        InfoDO infoDO = new InfoDO();

        infoDO.setIdNo("500235199909111981");
        infoDO.setName("孔田田");
        infoDO.setSex("1");
        LocalDateTime localDateTime = LocalDateTime.of(2016, 10, 17, 8, 8, 8);
        Date birthDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        infoDO.setBirthDate(birthDate);

        String clientNo = clientService.saveClientInfo(infoDO);
        Assert.assertNotNull(clientNo);
    }

    @Test
    public void updateClientInfo() {
        InfoDO infoDO = new InfoDO();

        infoDO.setClientNo("19000001");
        infoDO.setName("业齐齐");
        infoDO.setIdNo("500235199909111982");
        LocalDateTime localDateTime = LocalDateTime.of(2016, 11, 17, 8, 8, 8);
        Date birthDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        infoDO.setBirthDate(birthDate);

        clientService.updateClientInfo(infoDO);
    }

    @Test
    public void getClientInfo() {
        InfoDO infoDO = clientService.getClientInfo("19000001");
        Assert.assertNotNull(infoDO);
    }

    @Test
    public void listComplex_01() {
        InfoQueryDTO infoQueryDTO = new InfoQueryDTO();
        List<InfoDO> infoDOList = clientService.listComplex(infoQueryDTO);
        Assert.assertNotNull(infoDOList);
    }

    @Test
    public void listComplex_02() {
        InfoQueryDTO infoQueryDTO = new InfoQueryDTO();
        infoQueryDTO.setIdNo("500235199909991990");
        List<InfoDO> infoDOList = clientService.listComplex(infoQueryDTO);
        Assert.assertEquals(1, infoDOList.size());
    }

    @Test
    public void listComplex_03() {
        InfoQueryDTO infoQueryDTO = new InfoQueryDTO();
        infoQueryDTO.setClientNo("19000002");
        List<InfoDO> infoDOList = clientService.listComplex(infoQueryDTO);
        Assert.assertEquals(1, infoDOList.size());
    }
}