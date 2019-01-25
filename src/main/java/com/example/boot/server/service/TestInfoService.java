package com.example.boot.server.service;

import com.example.boot.server.pojo.ddo.test.TestInfoDO;

import java.util.List;

/**
 * @author liaowei
 */
public interface TestInfoService {
    public void saveTestInfo(TestInfoDO TestInfoDO);

    public void updateTestInfo(TestInfoDO TestInfoDO);

    public void deleteTestInfo(Integer id);

    public TestInfoDO queryTestInfoById(Integer id);

    public List<TestInfoDO> queryTestInfoList(TestInfoDO TestInfoDO);
}
