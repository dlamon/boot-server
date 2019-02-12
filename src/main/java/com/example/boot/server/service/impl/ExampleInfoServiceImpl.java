package com.example.boot.server.service.impl;

import com.example.boot.server.dao.example.ExampleInfoDao;
import com.example.boot.server.pojo.dos.example.ExampleInfoDO;
import com.example.boot.server.service.ExampleInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiaoWei
 */
@Service
public class ExampleInfoServiceImpl implements ExampleInfoService {
    final private ExampleInfoDao exampleInfoDao;

    public ExampleInfoServiceImpl(ExampleInfoDao exampleInfoDao) {
        this.exampleInfoDao = exampleInfoDao;
    }

    @Override
    public void saveExampleInfo(ExampleInfoDO exampleInfoDO) {
        exampleInfoDao.insert(exampleInfoDO);
    }

    @Override
    public void updateExampleInfo(ExampleInfoDO exampleInfoDO) {
        exampleInfoDao.updateByPrimaryKeySelective(exampleInfoDO);
    }

    @Override
    public void deleteExampleInfo(Integer id) {
        exampleInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public ExampleInfoDO queryExampleInfoById(Integer id) {
        return exampleInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public List<ExampleInfoDO> queryExampleInfoList(ExampleInfoDO exampleInfoDO) {
        return exampleInfoDao.select(exampleInfoDO);
    }
}
