package com.example.boot.server.service.impl;

import com.example.boot.server.dao.BasicInfoDao;
import com.example.boot.server.pojo.ddo.BasicInfoDO;
import com.example.boot.server.service.BasicInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaowei
 */
@Service
public class BasicInfoServiceImpl implements BasicInfoService {
    private BasicInfoDao basicInfoDao;

    public BasicInfoServiceImpl(BasicInfoDao basicInfoDao) {
        this.basicInfoDao = basicInfoDao;
    }

    @Override
    public void saveBasicInfo(BasicInfoDO basicInfoDO) {
        basicInfoDao.insert(basicInfoDO);
    }

    @Override
    public void updateBasicInfo(BasicInfoDO basicInfoDO) {
        basicInfoDao.updateByPrimaryKeySelective(basicInfoDO);
    }

    @Override
    public void deleteBasicInfo(Integer id) {
        basicInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public BasicInfoDO queryBasicInfoById(Integer id) {
        return basicInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicInfoDO> queryBasicInfoList(BasicInfoDO basicInfoDO) {
        return basicInfoDao.select(basicInfoDO);
    }
}
