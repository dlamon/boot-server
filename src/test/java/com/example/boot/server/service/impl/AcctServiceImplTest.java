package com.example.boot.server.service.impl;

import com.example.boot.server.dao.account.DetailDao;
import com.example.boot.server.dao.account.MasterDao;
import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dto.AcctQueryDTO;
import com.example.boot.server.pojo.dto.AcctResultDTO;
import common.BootExceptionMatcher;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.rules.ExpectedException;
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
public class AcctServiceImplTest {
    @Autowired
    MasterDao masterDao;
    @Autowired
    DetailDao detailDao;

    @Autowired
    AcctServiceImpl acctService;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        MasterDO masterDO = new MasterDO("6228671100001111", Short.valueOf("0"), "1234567890", new BigDecimal("100.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671100001112", Short.valueOf("1"), "1234567891", new BigDecimal("200.01"), null, null, null);
        masterDao.insert(masterDO);
        masterDO = new MasterDO("6228671100001113", Short.valueOf("0"), "1234567892", new BigDecimal("3000"), null, null, null);
        masterDao.insert(masterDO);

        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 8, 8, 8);
        Date createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        DetailDO detailDO = new DetailDO("6228671100001111", new BigDecimal("100"), new BigDecimal("3000"), "信用卡还款", createTime);
        detailDao.insert(detailDO);
        localDateTime = LocalDateTime.of(2015, 10, 17, 9, 9, 9);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        detailDO = new DetailDO("6228671100001111", new BigDecimal("200"), new BigDecimal("2800"), "代发工资", createTime);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671100001111", new BigDecimal("300.03"), new BigDecimal("2499.97"), "代发工资", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671100001111", new BigDecimal("499.97"), new BigDecimal("2000"), "交电费", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671100001111", new BigDecimal("1000"), new BigDecimal("1000"), "交房租", null);
        detailDao.insert(detailDO);
        localDateTime = LocalDateTime.of(2020, 10, 17, 9, 9, 9);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        detailDO = new DetailDO("6228671100001111", new BigDecimal("300"), new BigDecimal("700"), "交通费", createTime);
        detailDao.insert(detailDO);
    }

    @Test
    public void getMasterInfo() {
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertNotNull(masterDO);
    }

    @Test
    public void listDetailInfo() {
        List<DetailDO> detailDOList = acctService.listDetailInfo("6228671141418888");
        Assert.assertEquals(0, detailDOList.size());
    }

    @Test
    public void saveAccount() {
        MasterDO masterDO = new MasterDO();
        masterDO.setBalance(new BigDecimal("30000"));
        masterDO.setClientNo("1234567890");
        masterDO.setAcctStatus(Short.valueOf("0"));
        String acctNo = acctService.saveAccount(masterDO);
        log.info("acctNo: {}", acctNo);
    }

    @Test
    public void updateStatus() {
        acctService.updateStatus("6228671100001111", Short.valueOf("1"));
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals(1, masterDO.getAcctStatus().shortValue());
    }

    @Test
    public void updateClientId() {
        acctService.updateClientId("6228671100001111", "0987654321");
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals("0987654321", masterDO.getClientNo());
    }

    @Test
    public void updateRemark() {
        acctService.updateRemark("6228671100001111", "主要账户");
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals("主要账户", masterDO.getRemark());
    }

    @Test
    public void deposit_01() {
        expectedException.expect(new BootExceptionMatcher("ACT0002"));
        acctService.deposit("6228671100001114", new BigDecimal("3000"));
    }

    @Test
    public void deposit_02() {
        expectedException.expect(new BootExceptionMatcher("ACT0007"));
        expectedException.expectMessage("账户状态非正常");
        acctService.deposit("6228671100001112", new BigDecimal("3000"));
    }

    @Test
    public void deposit_03() {
        acctService.deposit("6228671100001113", new BigDecimal("3000"));
        MasterDO masterDO = acctService.getMasterInfo("6228671100001113");
        Assert.assertEquals(6000, masterDO.getBalance().longValue());
        List<DetailDO> detailDOList = acctService.listDetailInfo("6228671100001113");
        Assert.assertEquals(1, detailDOList.size());
        Assert.assertEquals(3000, detailDOList.get(0).getAmount().intValue());
    }

    @Test
    public void withdrawal_01() {
        expectedException.expect(new BootExceptionMatcher("ACT0002"));
        acctService.withdrawal("6228671100001114", new BigDecimal("3000"), "信用卡还款");
    }

    @Test
    public void withdrawal_02() {
        expectedException.expect(new BootExceptionMatcher("ACT0008"));
        expectedException.expectMessage("账户状态非正常");
        acctService.withdrawal("6228671100001112", new BigDecimal("3000"), "信用卡还款");
    }

    @Test
    public void withdrawal_03() {
        expectedException.expect(new BootExceptionMatcher("ACT0008"));
        expectedException.expectMessage("余额不足");
        acctService.withdrawal("6228671100001111", new BigDecimal("60000"), "信用卡还款");
    }

    @Test
    public void withdrawal_04() {
        acctService.withdrawal("6228671100001113", new BigDecimal("3000"), "信用卡还款");
        MasterDO masterDO = acctService.getMasterInfo("6228671100001113");
        Assert.assertEquals(0, masterDO.getBalance().longValue());
        List<DetailDO> detailDOList = acctService.listDetailInfo("6228671100001113");
        Assert.assertEquals(1, detailDOList.size());
        Assert.assertEquals(3000, detailDOList.get(0).getAmount().intValue());
    }

    @Test
    public void queryComplex_01() {
        AcctQueryDTO acctQueryDTO = new AcctQueryDTO();
        acctQueryDTO.setAcctNo("6228671100001111");
        AcctResultDTO acctResultDTO = acctService.listComplex(acctQueryDTO);

        Assert.assertNotNull(acctResultDTO);
        Assert.assertEquals(6, acctResultDTO.getAcctDetailList().size());
    }

    @Test
    public void queryComplex_02() {
        AcctQueryDTO acctQueryDTO = new AcctQueryDTO();
        acctQueryDTO.setAcctNo("6228671100001111");
        acctQueryDTO.setMinAmount(new BigDecimal("110"));
        acctQueryDTO.setMaxAmount(new BigDecimal("999"));
        AcctResultDTO acctResultDTO = acctService.listComplex(acctQueryDTO);
        log.info("{}", acctResultDTO.getAcctDetailList());

        Assert.assertNotNull(acctResultDTO);
        Assert.assertEquals(4, acctResultDTO.getAcctDetailList().size());
    }

    @Test
    public void queryComplex_03() {
        AcctQueryDTO acctQueryDTO = new AcctQueryDTO();
        acctQueryDTO.setAcctNo("6228671100001111");
        acctQueryDTO.setMinAmount(new BigDecimal("110"));
        acctQueryDTO.setMaxAmount(new BigDecimal("999"));
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 9, 10, 9);
        Date beginTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        acctQueryDTO.setBeginTime(beginTime);
        localDateTime = LocalDateTime.of(2020, 10, 17, 9, 8, 9);
        Date endTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        acctQueryDTO.setEndTime(endTime);
        AcctResultDTO acctResultDTO = acctService.listComplex(acctQueryDTO);
        log.info("{}", acctResultDTO.getAcctDetailList());

        Assert.assertNotNull(acctResultDTO);
        Assert.assertEquals(2, acctResultDTO.getAcctDetailList().size());
    }

    @Test
    public void queryComplex_04() {
        AcctQueryDTO acctQueryDTO = new AcctQueryDTO();
        acctQueryDTO.setAcctNo("6228671100001111");
        acctQueryDTO.setMinAmount(new BigDecimal("110"));
        acctQueryDTO.setMaxAmount(new BigDecimal("999"));
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 9, 10, 9);
        Date beginTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        acctQueryDTO.setBeginTime(beginTime);
        localDateTime = LocalDateTime.of(2020, 10, 17, 9, 8, 9);
        Date endTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        acctQueryDTO.setEndTime(endTime);
        acctQueryDTO.setSort("amount");
        acctQueryDTO.setOrder("desc");
        AcctResultDTO acctResultDTO = acctService.listComplex(acctQueryDTO);
        log.info("{}", acctResultDTO.getAcctDetailList());

        Assert.assertNotNull(acctResultDTO);
        Assert.assertEquals(2, acctResultDTO.getAcctDetailList().size());
    }

    @Test
    public void queryComplex_05() {
        AcctQueryDTO acctQueryDTO = new AcctQueryDTO();
        acctQueryDTO.setAcctNo("6228671100001111");
        acctQueryDTO.setMinAmount(new BigDecimal("110"));
        acctQueryDTO.setMaxAmount(new BigDecimal("999"));
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 9, 10, 9);
        Date beginTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        acctQueryDTO.setBeginTime(beginTime);
        localDateTime = LocalDateTime.of(2020, 10, 17, 9, 8, 9);
        Date endTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        acctQueryDTO.setEndTime(endTime);
        acctQueryDTO.setSort("amount");
        acctQueryDTO.setOrder("desc");

        acctQueryDTO.setPageNum(1);
        acctQueryDTO.setPageSize(1);
        AcctResultDTO acctResultDTO = acctService.listComplex(acctQueryDTO);
        Assert.assertNotNull(acctResultDTO);
        Assert.assertEquals(1, acctResultDTO.getAcctDetailList().size());

        acctQueryDTO.setPageNum(2);
        acctQueryDTO.setPageSize(1);
        acctResultDTO = acctService.listComplex(acctQueryDTO);
        Assert.assertNotNull(acctResultDTO);
        Assert.assertEquals(1, acctResultDTO.getAcctDetailList().size());
    }
}