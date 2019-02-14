package com.example.boot.server.service.impl;

import com.example.boot.server.dao.account.DetailDao;
import com.example.boot.server.dao.account.MasterDao;
import com.example.boot.server.enums.AcctStatusEnum;
import com.example.boot.server.exception.BootException;
import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dto.AcctDTO;
import com.example.boot.server.service.AcctService;
import com.github.pagehelper.PageRowBounds;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiaoWei
 */
@Service
public class AcctServiceImpl implements AcctService {
    private MasterDao masterDao;
    private DetailDao detailDao;

    public AcctServiceImpl(MasterDao masterDao, DetailDao detailDao) {
        this.masterDao = masterDao;
        this.detailDao = detailDao;
    }

    @Override
    public void addAccount(MasterDO masterDO) {
        // 查询账户是否存在
        MasterDO result = masterDao.selectOne(masterDO);
        if (result != null) {
            throw new BootException("ACT0001");
        }
        // 添加账户
        int rows = masterDao.insertSelective(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0003");
        }
    }

    @Override
    public void changeStatus(String acctNo, Short acctStatus) {
        // 查询账户
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo(acctNo);
        masterDO = masterDao.selectOne(masterDO);
        if (masterDO == null) {
            throw new BootException("ACT0002");
        }
        // 更新账户状态
        masterDO.setAcctStatus(acctStatus);
        int rows = masterDao.updateByPrimaryKey(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0004");
        }
    }

    @Override
    public void changeClientId(String acctNo, String clientNo) {
        // 查询账户
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo(acctNo);
        masterDO = masterDao.selectOne(masterDO);
        if (masterDO == null) {
            throw new BootException("ACT0002");
        }
        // 更新账户对应的客户号
        masterDO.setClientNo(clientNo);
        int rows = masterDao.updateByPrimaryKey(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0005");
        }
    }

    @Override
    public void changeRemark(String acctNo, String remark) {
        // 查询账户
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo(acctNo);
        masterDO = masterDao.selectOne(masterDO);
        if (masterDO == null) {
            throw new BootException("ACT0002");
        }
        // 更新账户备注信息
        masterDO.setRemark(remark);
        int rows = masterDao.updateByPrimaryKey(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0006");
        }
    }

    @Override
    public void deposit(String acctNo, BigDecimal amount) {
        // 查询账户
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo(acctNo);
        masterDO = masterDao.selectOne(masterDO);
        if (masterDO == null) {
            throw new BootException("ACT0002");
        }
        // 判断账户状态是否正常
        if (AcctStatusEnum.ABNORMAL.getKey().equals(masterDO.getAcctStatus())) {
            throw new BootException("ACT0007");
        }
        // 计算余额
        BigDecimal balance = masterDO.getBalance();
        BigDecimal newBalance = balance.add(amount);
        // 更改主表金额
        masterDO.setBalance(newBalance);
        int rows = masterDao.updateByPrimaryKey(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0008");
        }
        // 记录明细数据
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo(masterDO.getAcctNo());
        detailDO.setAmount(amount);
        detailDO.setBalance(newBalance);
        rows = detailDao.insert(detailDO);
        if (rows != 1) {
            throw new BootException("ACT0008");
        }
    }

    @Override
    public void withdrawal(String acctNo, BigDecimal amount, String use) {
        // 查询账户
        MasterDO masterDO = new MasterDO();
        masterDO.setAcctNo(acctNo);
        masterDO = masterDao.selectOne(masterDO);
        if (masterDO == null) {
            throw new BootException("ACT0002");
        }
        // 判断账户状态是否正常
        if (AcctStatusEnum.ABNORMAL.getKey().equals(masterDO.getAcctStatus())) {
            throw new BootException("ACT0007");
        }
        // 判断账户余额是否足够
        BigDecimal balance = masterDO.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new BootException("ACT0009", "余额不足");
        }
        // 计算余额
        BigDecimal newBalance = balance.subtract(amount);
        masterDO.setBalance(newBalance);
        int rows = masterDao.updateByPrimaryKey(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0009");
        }
        // 记录明细数据
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo(masterDO.getAcctNo());
        detailDO.setAmount(amount);
        detailDO.setBalance(newBalance);
        rows = detailDao.insert(detailDO);
        if (rows != 1) {
            throw new BootException("ACT0009");
        }
    }

    @Override
    public AcctDTO query(String acctNo, Date beginDate, Date endDate, BigDecimal maxAmount, BigDecimal minAmount, PageRowBounds bounds) {
        return null;
    }
}
