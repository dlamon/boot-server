package com.example.boot.server.service;

import com.example.boot.server.model.BasicInfo;

import java.util.List;

/**
 * @author liaowei
 */
public interface BasicInfoService {
    public void saveBasicInfo(BasicInfo basicInfo);

    public void updateBasicInfo(BasicInfo basicInfo);

    public void deleteBasicInfo(Integer id);

    public BasicInfo queryBasicInfoById(Integer id);

    public List<BasicInfo> queryBasicInfoList(BasicInfo basicInfo);
}
