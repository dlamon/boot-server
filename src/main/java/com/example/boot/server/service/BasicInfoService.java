package com.example.boot.server.service;

import com.example.boot.server.pojo.ddo.BasicInfoDO;

import java.util.List;

/**
 * @author liaowei
 */
public interface BasicInfoService {
    public void saveBasicInfo(BasicInfoDO basicInfoDO);

    public void updateBasicInfo(BasicInfoDO basicInfoDO);

    public void deleteBasicInfo(Integer id);

    public BasicInfoDO queryBasicInfoById(Integer id);

    public List<BasicInfoDO> queryBasicInfoList(BasicInfoDO basicInfoDO);
}
