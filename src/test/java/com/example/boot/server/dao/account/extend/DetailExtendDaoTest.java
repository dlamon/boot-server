package com.example.boot.server.dao.account.extend;

import com.example.boot.server.dao.account.DetailDao;
import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dto.DetailQueryDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class DetailExtendDaoTest {
    @Autowired
    MasterExtendDao masterExtendDao;

    @Autowired
    DetailDao detailDao;
    @Autowired
    DetailExtendDao detailExtendDao;

    @Before
    public void setUp() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 8, 8, 8);
        Date createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        DetailDO detailDO = new DetailDO("6228671133331111", new BigDecimal("100"), new BigDecimal("3000"), "信用卡还款", createTime);
        detailDao.insert(detailDO);
        localDateTime = LocalDateTime.of(2015, 10, 17, 9, 9, 9);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        detailDO = new DetailDO("6228671133331111", new BigDecimal("200"), new BigDecimal("2800"), "代发工资", createTime);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331111", new BigDecimal("300.03"), new BigDecimal("2499.97"), "代发工资", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331111", new BigDecimal("499.97"), new BigDecimal("2000"), "交电费", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331111", new BigDecimal("1000"), new BigDecimal("1000"), "交房租", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331111", new BigDecimal("300"), new BigDecimal("700"), "交通费", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331112", new BigDecimal("333"), new BigDecimal("8000"), "交通讯费", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331112", new BigDecimal("3000"), new BigDecimal("5000"), "信用卡还款", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331112", new BigDecimal("4000"), new BigDecimal("1000"), "交学费", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331112", new BigDecimal("333"), new BigDecimal("8000"), "交通讯费", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331112", new BigDecimal("3000"), new BigDecimal("5000"), "信用卡还款", null);
        detailDao.insert(detailDO);
        detailDO = new DetailDO("6228671133331113", new BigDecimal("8000"), new BigDecimal("10000"), "交房贷", null);
        detailDao.insert(detailDO);
    }

    @Test
    public void selectAllByConditions() {
        // 查询所有数据
        log.debug("--- Select All by no conditions ---");
        DetailQueryDTO detailQueryDTO = new DetailQueryDTO();
        List<DetailDO> detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(12, detailDOList.size());

        // 通过账号查询
        log.debug("--- Select All by acctNo ---");
        detailQueryDTO = new DetailQueryDTO();
        detailQueryDTO.setAcctNo("6228671133331111");
        detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(6, detailDOList.size());

        // 通过账号和发生金额查询
        log.debug("--- Select All by acctNo, amount ---");
        detailQueryDTO = new DetailQueryDTO();
        detailQueryDTO.setAcctNo("6228671133331111");
        detailQueryDTO.setAmount(new BigDecimal("199"));
        detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(5, detailDOList.size());

        // 通过账号、发生金额和账户余额查询
        log.debug("--- Select All by acctNo, amount, balance ---");
        detailQueryDTO = new DetailQueryDTO();
        detailQueryDTO.setAcctNo("6228671133331111");
        detailQueryDTO.setAmount(new BigDecimal("199"));
        detailQueryDTO.setBalance(new BigDecimal("800"));
        detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(4, detailDOList.size());

        // 通过账号、发生金额、账户余额和创建时间查询
        log.debug("--- Select All by acctNo, amount, balance, createTime ---");
        detailQueryDTO = new DetailQueryDTO();
        detailQueryDTO.setAcctNo("6228671133331111");
        detailQueryDTO.setAmount(new BigDecimal("199"));
        detailQueryDTO.setBalance(new BigDecimal("800"));
        LocalDateTime localDateTime = LocalDateTime.of(2015, 10, 17, 10, 10, 10);
        Date createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        detailQueryDTO.setCreateTime(createTime);
        detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(3, detailDOList.size());

        // 通过账号、发生金额、账户余额和创建时间查询, 并按照发生金额倒序排列
        log.debug("--- Select All by acctNo, amount, balance, createTime order by amount desc ---");
        detailQueryDTO = new DetailQueryDTO();
        detailQueryDTO.setAcctNo("6228671133331111");
        detailQueryDTO.setAmount(new BigDecimal("199"));
        detailQueryDTO.setBalance(new BigDecimal("800"));
        localDateTime = LocalDateTime.of(2015, 10, 17, 10, 10, 10);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        detailQueryDTO.setCreateTime(createTime);
        detailQueryDTO.setSort("amount");
        detailQueryDTO.setOrder("desc");
        detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(3, detailDOList.size());

        // 通过账号、发生金额、账户余额和创建时间查询, 按照发生金额倒序排列, 进行分页查询，一页两条记录
        log.debug("--- Select All by acctNo, amount, balance, createTime createTime order by amount desc pageNum ---");
        detailQueryDTO = new DetailQueryDTO();
        detailQueryDTO.setAcctNo("6228671133331111");
        detailQueryDTO.setAmount(new BigDecimal("199"));
        detailQueryDTO.setBalance(new BigDecimal("800"));
        localDateTime = LocalDateTime.of(2015, 10, 17, 10, 10, 10);
        createTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        detailQueryDTO.setCreateTime(createTime);
        detailQueryDTO.setSort("amount");
        detailQueryDTO.setOrder("desc");
        detailQueryDTO.setPageNum(1);
        detailQueryDTO.setPageSize(2);
        log.debug("--- pageNum: 1 ---");
        PageHelper.startPage(detailQueryDTO.getPageNum(), detailQueryDTO.getPageSize());
        detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(2, detailDOList.size());
        Assert.assertEquals(3, ((Page)detailDOList).getTotal());
        log.debug("--- pageNum: 2 ---");
        detailQueryDTO.setPageNum(2);
        PageHelper.startPage(detailQueryDTO.getPageNum(), detailQueryDTO.getPageSize());
        detailDOList = detailExtendDao.selectAllByConditions(detailQueryDTO);
        detailDOList.forEach(element -> log.debug("{}", element));
        Assert.assertEquals(1, detailDOList.size());
        Assert.assertEquals(3, ((Page)detailDOList).getTotal());
    }
}