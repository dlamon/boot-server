package com.example.boot.server.service;

import com.example.boot.server.pojo.ddo.example.ExampleInfoDO;

import java.util.List;

/**
 * @author liaowei
 */
public interface ExampleInfoService {
    public void saveExampleInfo(ExampleInfoDO ExampleInfoDO);

    public void updateExampleInfo(ExampleInfoDO ExampleInfoDO);

    public void deleteExampleInfo(Integer id);

    public ExampleInfoDO queryExampleInfoById(Integer id);

    public List<ExampleInfoDO> queryExampleInfoList(ExampleInfoDO ExampleInfoDO);
}
