package com.example.boot.server.service.impl;

import com.example.boot.server.dao.example.ExampleInfoDao;
import com.example.boot.server.pojo.ddo.example.ExampleInfoDO;
import com.example.boot.server.service.ExampleInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaowei
 */
@Service
public class ExampleInfoServiceImpl implements ExampleInfoService {
    private ExampleInfoDao ExampleInfoDao;

    public ExampleInfoServiceImpl(ExampleInfoDao exampleInfoDao) {
        this.ExampleInfoDao = exampleInfoDao;
    }

    @Override
    public void saveExampleInfo(ExampleInfoDO exampleInfoDO) {
        ExampleInfoDao.insert(exampleInfoDO);
    }

    @Override
    public void updateExampleInfo(ExampleInfoDO exampleInfoDO) {
        ExampleInfoDao.updateByPrimaryKeySelective(exampleInfoDO);
    }

    @Override
    public void deleteExampleInfo(Integer id) {
        ExampleInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public ExampleInfoDO queryExampleInfoById(Integer id) {
        return ExampleInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public List<ExampleInfoDO> queryExampleInfoList(ExampleInfoDO exampleInfoDO) {
        return ExampleInfoDao.select(exampleInfoDO);
    }
}
