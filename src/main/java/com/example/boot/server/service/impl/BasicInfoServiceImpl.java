package com.example.boot.server.service.impl;

import com.example.boot.server.mapper.BasicInfoMapper;
import com.example.boot.server.model.BasicInfo;
import com.example.boot.server.service.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaowei
 */
@Service
public class BasicInfoServiceImpl implements BasicInfoService {
    @Autowired
    private BasicInfoMapper basicInfoMapper;

    @Override
    public void saveBasicInfo(BasicInfo basicInfo) {
        basicInfoMapper.insert(basicInfo);
    }

    @Override
    public void updateBasicInfo(BasicInfo basicInfo) {
        basicInfoMapper.updateByPrimaryKeySelective(basicInfo);
    }

    @Override
    public void deleteBasicInfo(Integer id) {
        basicInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void queryBasicInfoById(Integer id) {
        basicInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicInfo> queryBasicInfoList(BasicInfo basicInfo) {
        return null;
    }
}
