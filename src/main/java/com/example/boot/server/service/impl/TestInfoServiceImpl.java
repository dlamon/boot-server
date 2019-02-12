package com.example.boot.server.service.impl;

import com.example.boot.server.dao.test.TestInfoDao;
import com.example.boot.server.pojo.dos.test.TestInfoDO;
import com.example.boot.server.service.TestInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiaoWei
 */
@Service
public class TestInfoServiceImpl implements TestInfoService {
    final private TestInfoDao testInfoDao;

    public TestInfoServiceImpl(TestInfoDao testInfoDao) {
        this.testInfoDao = testInfoDao;
    }

    @Override
    public void saveTestInfo(TestInfoDO testInfoDO) {
        testInfoDao.insert(testInfoDO);
    }

    @Override
    public void updateTestInfo(TestInfoDO testInfoDO) {
        testInfoDao.updateByPrimaryKeySelective(testInfoDO);
    }

    @Override
    public void deleteTestInfo(Integer id) {
        testInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public TestInfoDO queryTestInfoById(Integer id) {
        return testInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public List<TestInfoDO> queryTestInfoList(TestInfoDO testInfoDO) {
        return testInfoDao.select(testInfoDO);
    }
}
