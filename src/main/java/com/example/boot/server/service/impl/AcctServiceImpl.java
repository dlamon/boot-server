package com.example.boot.server.service.impl;

import com.example.boot.server.dao.account.DetailDao;
import com.example.boot.server.dao.account.MasterDao;
import com.example.boot.server.dao.account.extend.DetailExtendDao;
import com.example.boot.server.dao.account.extend.MasterExtendDao;
import com.example.boot.server.enums.AcctStatusEnum;
import com.example.boot.server.exception.BootException;
import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dto.AcctQueryDTO;
import com.example.boot.server.pojo.dto.DetailQueryDTO;
import com.example.boot.server.service.AcctService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LiaoWei
 */
@Service
public class AcctServiceImpl implements AcctService {
    private MasterDao masterDao;
    private DetailDao detailDao;
    private MasterExtendDao masterExtendDao;
    private DetailExtendDao detailExtendDao;

    public AcctServiceImpl(MasterDao masterDao, DetailDao detailDao, MasterExtendDao masterExtendDao, DetailExtendDao detailExtendDao) {
        this.masterDao = masterDao;
        this.detailDao = detailDao;
        this.masterExtendDao = masterExtendDao;
        this.detailExtendDao = detailExtendDao;
    }

    @Override
    public MasterDO getMasterInfo(String acctNo) {
        MasterDO masterDO = masterDao.selectByPrimaryKey(acctNo);
        return masterDO;
    }

    @Override
    public List<DetailDO> listDetailInfo(String acctNo) {
        DetailQueryDTO detailQueryDTO = new DetailQueryDTO();
        detailQueryDTO.setAcctNo(acctNo);
        List<DetailDO> detailDOList= detailExtendDao.selectAllByConditions(detailQueryDTO);
        return detailDOList;
    }

    @Override
    public void saveAccount(MasterDO masterDO) {
        // 查询账户是否存在
        MasterDO result = this.getMasterInfo(masterDO.getAcctNo());
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
    public void updateStatus(String acctNo, Short acctStatus) {
        // 查询账户
        MasterDO masterDO = this.getMasterInfo(acctNo);
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
    public void updateClientId(String acctNo, String clientNo) {
        // 查询账户
        MasterDO masterDO = this.getMasterInfo(acctNo);
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
    public void updateRemark(String acctNo, String remark) {
        // 查询账户
        MasterDO masterDO  = this.getMasterInfo(acctNo);
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
    @Transactional
    public void deposit(String acctNo, BigDecimal amount) {
        // 查询账户
        MasterDO masterDO = this.getMasterInfo(acctNo);
        if (masterDO == null) {
            throw new BootException("ACT0002");
        }
        // 判断账户状态是否正常
        if (AcctStatusEnum.ABNORMAL.getKey().equals(masterDO.getAcctStatus())) {
            throw new BootException("ACT0007", "账户状态非正常");
        }
        // 计算余额
        BigDecimal balance = masterDO.getBalance();
        BigDecimal newBalance = balance.add(amount);
        // 更改主表金额
        masterDO.setBalance(newBalance);
        int rows = masterDao.updateByPrimaryKey(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0007", "更新数据失败");
        }
        // 记录明细数据
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo(masterDO.getAcctNo());
        detailDO.setAmount(amount);
        detailDO.setBalance(newBalance);
        rows = detailDao.insert(detailDO);
        if (rows != 1) {
            throw new BootException("ACT0007", "更新明细失败");
        }
    }

    @Override
    @Transactional
    public void withdrawal(String acctNo, BigDecimal amount, String use) {
        // 查询账户
        MasterDO masterDO = this.getMasterInfo(acctNo);
        if (masterDO == null) {
            throw new BootException("ACT0002");
        }
        // 判断账户状态是否正常
        if (AcctStatusEnum.ABNORMAL.getKey().equals(masterDO.getAcctStatus())) {
            throw new BootException("ACT0008", "账户状态非正常");
        }
        // 判断账户余额是否足够
        BigDecimal balance = masterDO.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new BootException("ACT0008", "余额不足");
        }
        // 计算余额
        BigDecimal newBalance = balance.subtract(amount);
        masterDO.setBalance(newBalance);
        int rows = masterDao.updateByPrimaryKey(masterDO);
        if (rows != 1) {
            throw new BootException("ACT0008", "更新数据失败");
        }
        // 记录明细数据
        DetailDO detailDO = new DetailDO();
        detailDO.setAcctNo(masterDO.getAcctNo());
        detailDO.setAmount(amount);
        detailDO.setBalance(newBalance);
        rows = detailDao.insert(detailDO);
        if (rows != 1) {
            throw new BootException("ACT0008", "更新明细失败");
        }
    }

    @Override
    public AcctQueryDTO queryComplex(AcctQueryDTO acctQueryDTO) {
        return null;
    }
}
