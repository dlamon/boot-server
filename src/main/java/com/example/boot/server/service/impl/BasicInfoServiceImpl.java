package com.example.boot.server.service.impl;

import com.example.boot.server.mapper.BasicInfoMapper;
import com.example.boot.server.model.BasicInfo;
import com.example.boot.server.service.BasicInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaowei
 */
@Service
public class BasicInfoServiceImpl implements BasicInfoService {
    private BasicInfoMapper basicInfoMapper;

    public BasicInfoServiceImpl(BasicInfoMapper basicInfoMapper) {
        this.basicInfoMapper = basicInfoMapper;
    }

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
    public BasicInfo queryBasicInfoById(Integer id) {
        return basicInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicInfo> queryBasicInfoList(BasicInfo basicInfo) {
        return basicInfoMapper.select(basicInfo);
    }
}
