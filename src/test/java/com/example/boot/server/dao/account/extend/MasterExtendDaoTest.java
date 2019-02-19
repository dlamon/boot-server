package com.example.boot.server.dao.account.extend;

import com.example.boot.server.dao.account.MasterDao;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dto.MasterQueryDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    @Before
    public void setUp() throws Exception {
        MasterDO masterDO = new MasterDO("6228671133331111", Short.valueOf("0"), "19000001", new BigDecimal("100.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331112", Short.valueOf("0"), "19000001", new BigDecimal("200.01"), null, null, null);
        masterDao.insert(masterDO);
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 8, 8, 8);
        Date createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        masterDO = new MasterDO("6228671133331113", Short.valueOf("0"), "19000001", new BigDecimal("300.01"), null, createTime, null);
        masterDao.insert(masterDO);
        localDateTime = LocalDateTime.of(2015, 10, 17, 9, 9, 9);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        masterDO = new MasterDO("6228671133331114", Short.valueOf("0"), "19000001", new BigDecimal("400.01"), null, createTime, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331115", Short.valueOf("0"), "19000001", new BigDecimal("500.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671133331116", Short.valueOf("0"), "19000001", new BigDecimal("600.01"), null, null, null);
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
        String clientNo = "19000001";
        // 查询所有满足条件的记录
        log.debug("Select All by clientNo-->");
        List<MasterDO> masterDOList = masterExtendDao.selectAllByClientNo(clientNo);
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));
        Assert.assertEquals(7, masterDOList.size());

        // 分页查询满足条件的记录（pageNum: 0, pageSize: 5）
        log.debug("Select All by clientNo pageNum: 0 pageSize: 5 -->");
        PageRowBounds pageRowBounds = new PageRowBounds(0, 5);
        masterDOList = masterExtendDao.selectAllByClientNo(clientNo, pageRowBounds);
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));
        Assert.assertEquals(5, masterDOList.size());
        Assert.assertEquals(7, pageRowBounds.getTotal().longValue());

        // 分页查询满足条件的记录（pageNum: 5, pageSize: 5）
        log.debug("Select All by clientNo pageNum: 5 pageSize: 5 -->");
        pageRowBounds = new PageRowBounds(5, 5);
        masterDOList = masterExtendDao.selectAllByClientNo(clientNo, pageRowBounds);
        masterDOList.forEach(masterDO -> log.debug("{}", masterDO));
        Assert.assertEquals(2, masterDOList.size());
        Assert.assertEquals(7, pageRowBounds.getTotal().longValue());
    }

    @Test
    public void selectAllByConditions() {
        // 查询所有数据
        log.debug("--- Select All by no conditions ---");
        MasterQueryDTO masterQueryDTO = new MasterQueryDTO();
        List<MasterDO> masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(12, masterDOList.size());

        // 通过账号查询账户
        log.debug("--- Select All by acctNo ---");
        masterQueryDTO = new MasterQueryDTO();
        masterQueryDTO.setAcctNo("6228671133331111");
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(1, masterDOList.size());

        // 通过账号状态查询账户
        log.debug("--- Select All by acctStatus ---");
        masterQueryDTO = new MasterQueryDTO();
        masterQueryDTO.setAcctStatus(Short.valueOf("0"));
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(9, masterDOList.size());

        // 通过账户状态和客户号查询账户
        log.debug("--- Select All by acctStatus, clientNo ---");
        masterQueryDTO = new MasterQueryDTO();
        masterQueryDTO.setAcctStatus(Short.valueOf("0"));
        masterQueryDTO.setClientNo("19000001");
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(7, masterDOList.size());

        // 通过账户状态、客户号和余额查询账户
        log.debug("--- Select All by acctStatus, clientNo, balance ---");
        masterQueryDTO = new MasterQueryDTO();
        masterQueryDTO.setAcctStatus(Short.valueOf("0"));
        masterQueryDTO.setClientNo("19000001");
        masterQueryDTO.setBalance(new BigDecimal("300"));
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(5, masterDOList.size());

        // 通过账户状态、客户号、余额和创建时间查询账户
        log.debug("--- Select All by acctStatus, clientNo, balance, createTime ---");
        masterQueryDTO = new MasterQueryDTO();
        masterQueryDTO.setAcctStatus(Short.valueOf("0"));
        masterQueryDTO.setClientNo("19000001");
        masterQueryDTO.setBalance(new BigDecimal("300"));
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 10, 10, 10);
        Date createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        masterQueryDTO.setCreateTime(createTime);
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(3, masterDOList.size());

        // 通过账户状态、客户号、余额和创建时间查询账户, 并按照金额倒序排列
        log.debug("--- Select All by acctStatus, clientNo, balance, createTime, order by balance desc ---");
        masterQueryDTO = new MasterQueryDTO();
        masterQueryDTO.setAcctStatus(Short.valueOf("0"));
        masterQueryDTO.setClientNo("19000001");
        masterQueryDTO.setBalance(new BigDecimal("300"));
        localDateTime = LocalDateTime.of(2015, 10, 17, 10, 10, 10);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        masterQueryDTO.setCreateTime(createTime);
        masterQueryDTO.setSort("balance");
        masterQueryDTO.setOrder("desc");
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(3, masterDOList.size());

        // 通过账户状态、客户号、余额和创建时间查询账户, 按照金额倒序排列, 进行分页查询，一页两条记录
        log.debug("--- Select All by acctStatus, clientNo, balance, createTime, order by balance desc pageNum ---");
        masterQueryDTO = new MasterQueryDTO();
        masterQueryDTO.setAcctStatus(Short.valueOf("0"));
        masterQueryDTO.setClientNo("19000001");
        masterQueryDTO.setBalance(new BigDecimal("300"));
        localDateTime = LocalDateTime.of(2015, 10, 17, 10, 10, 10);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        masterQueryDTO.setCreateTime(createTime);
        masterQueryDTO.setSort("balance");
        masterQueryDTO.setOrder("desc");
        masterQueryDTO.setPageNum(1);
        masterQueryDTO.setPageSize(2);
        log.debug("--- pageNum: 1 ---");
        PageHelper.startPage(masterQueryDTO.getPageNum(), masterQueryDTO.getPageSize());
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(2, masterDOList.size());
        Assert.assertEquals(3, ((Page)masterDOList).getTotal());
        log.debug("--- pageNum: 2 ---");
        masterQueryDTO.setPageNum(2);
        PageHelper.startPage(masterQueryDTO.getPageNum(), masterQueryDTO.getPageSize());
        masterDOList = masterExtendDao.selectAllByConditions(masterQueryDTO);
        masterDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(1, masterDOList.size());
        Assert.assertEquals(3, ((Page)masterDOList).getTotal());
    }
}