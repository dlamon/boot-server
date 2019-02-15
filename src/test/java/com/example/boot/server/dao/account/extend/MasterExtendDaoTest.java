package com.example.boot.server.dao.account.extend;

import com.example.boot.server.dao.account.MasterDao;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.github.pagehelper.PageRowBounds;
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
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional
@Slf4j
public class MasterExtendDaoTest {
    @Autowired
    MasterDao masterDao;
    @Autowired
    MasterExtendDao masterExtendDao;

    private void insertMock() {
        MasterDO masterDO = new MasterDO("6228671133331111", Short.valueOf("0"), "19000001", new BigDecimal("100.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331112", Short.valueOf("1"), "19000001", new BigDecimal("200.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331113", Short.valueOf("0"), "19000001", new BigDecimal("300.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331114", Short.valueOf("1"), "19000001", new BigDecimal("400.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331115", Short.valueOf("0"), "19000001", new BigDecimal("500.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331116", Short.valueOf("1"), "19000001", new BigDecimal("600.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331117", Short.valueOf("0"), "19000001", new BigDecimal("700.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331118", Short.valueOf("1"), "19000002", new BigDecimal("800.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331119", Short.valueOf("0"), "19000002", new BigDecimal("900.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331120", Short.valueOf("1"), "19000003", new BigDecimal("200.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331121", Short.valueOf("0"), "19000004", new BigDecimal("210.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331122", Short.valueOf("1"), "19000004", new BigDecimal("220.01"), null, null, null);
        masterDao.insert(masterDO);
    }

    @Test
    public void selectAllByClientNo() {
        this.insertMock();
        String clientNo = "19000001";
        // 查询所有满足条件的记录
        log.debug("Select All by clientNo-->");
        List<MasterDO> masterDOList = masterExtendDao.selectAllByClientNo(clientNo);
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));

        // 分页查询满足条件的记录（offset: 0, limit: 5）
        log.debug("Select All by clientNo offset: 0 limit: 5 -->");
        PageRowBounds pageRowBounds = new PageRowBounds(0, 5);
        masterDOList = masterExtendDao.selectAllByClientNo(clientNo, pageRowBounds);
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));

        // 分页查询满足条件的记录（offset: 5, limit: 5）
        log.debug("Select All by clientNo offset: 5 limit: 5 -->");
        pageRowBounds = new PageRowBounds(5, 5);
        masterDOList = masterExtendDao.selectAllByClientNo(clientNo, pageRowBounds);
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));
    }

    @Test
    public void selectAllByConditions() {

    }
}