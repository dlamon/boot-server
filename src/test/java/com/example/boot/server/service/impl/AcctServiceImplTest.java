package com.example.boot.server.service.impl;

import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dos.account.MasterDO;
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
import java.util.List;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional
@Slf4j
public class AcctServiceImplTest {
    @Autowired
    AcctServiceImpl acctService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671100001111");
        masterDO.setBalance(new BigDecimal("30000"));
        masterDO.setClientNo("1234567890");
        masterDO.setAcctStatus(Short.valueOf("0"));
        acctService.saveAccount(masterDO);

        masterDO = new MasterDO();
        masterDO.setAcctNo("6228671100001112");
        masterDO.setBalance(new BigDecimal("60000"));
        masterDO.setClientNo("1234567891");
        masterDO.setAcctStatus(Short.valueOf("1"));
        acctService.saveAccount(masterDO);
    }

    @Test
    public void queryMaster() {
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertNotNull(masterDO);
    }

    @Test
    public void queryDetail() {
        List<DetailDO> detailDOList = acctService.listDetailInfo("6228671141418888");
        Assert.assertEquals(0, detailDOList.size());
    }

    @Test
    public void addAccount() {
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo("6228671100001113");
        masterDO.setBalance(new BigDecimal("30000"));
        masterDO.setClientNo("1234567890");
        masterDO.setAcctStatus(Short.valueOf("0"));
        acctService.saveAccount(masterDO);
    }

    @Test
    public void addAccountException() {
        this.addAccount();
        expectedException.expect(new BootExceptionMatcher("ACT0001"));
        this.addAccount();
    }

    @Test
    public void changeStatus() {
        acctService.updateStatus("6228671100001111", Short.valueOf("1"));
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals(1, masterDO.getAcctStatus().shortValue());
    }

    @Test
    public void changeClientId() {
        acctService.updateClientId("6228671100001111", "0987654321");
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals("0987654321", masterDO.getClientNo());
    }

    @Test
    public void changeRemark() {
        acctService.updateRemark("6228671100001111", "主要账户");
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals("主要账户", masterDO.getRemark());
    }

    @Test
    public void deposit_01() {
        expectedException.expect(new BootExceptionMatcher("ACT0002"));
        acctService.deposit("6228671100001113", new BigDecimal("3000"));
    }

    @Test
    public void deposit_02() {
        expectedException.expect(new BootExceptionMatcher("ACT0007"));
        expectedException.expectMessage("账户状态非正常");
        acctService.deposit("6228671100001112", new BigDecimal("3000"));
    }

    @Test
    public void deposit_03() {
        acctService.deposit("6228671100001111", new BigDecimal("3000"));
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals(33000, masterDO.getBalance().longValue());
        List<DetailDO> detailDOList = acctService.listDetailInfo("6228671100001111");
        Assert.assertEquals(1, detailDOList.size());
        Assert.assertEquals(3000, detailDOList.get(0).getAmount().intValue());
    }

    @Test
    public void withdrawal_01() {
        expectedException.expect(new BootExceptionMatcher("ACT0002"));
        acctService.withdrawal("6228671100001113", new BigDecimal("3000"), "信用卡还款");
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
        acctService.withdrawal("6228671100001111", new BigDecimal("3000"), "信用卡还款");
        MasterDO masterDO = acctService.getMasterInfo("6228671100001111");
        Assert.assertEquals(27000, masterDO.getBalance().longValue());
        List<DetailDO> detailDOList = acctService.listDetailInfo("6228671100001111");
        Assert.assertEquals(1, detailDOList.size());
        Assert.assertEquals(3000, detailDOList.get(0).getAmount().intValue());
    }
}