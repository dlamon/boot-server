package com.example.boot.server.service;

import com.example.boot.server.pojo.dos.test.TestInfoDO;

import java.util.List;

/**
 * @author LiaoWei
 */
public interface TestInfoService {
    /**
     * 保存信息
     * @param testInfoDO
     */
    void saveTestInfo(TestInfoDO testInfoDO);

    /**
     * 更新信息
     * @param testInfoDO
     */
    void updateTestInfo(TestInfoDO testInfoDO);

    /**
     * 删除信息
     * @param id
     */
    void deleteTestInfo(Integer id);

    /**
     * 查询信息
     * @param id
     * @return
     */
    TestInfoDO queryTestInfoById(Integer id);

    /**
     * 查询信息列表
     * @param testInfoDO
     * @return
     */
    List<TestInfoDO> queryTestInfoList(TestInfoDO testInfoDO);
}
