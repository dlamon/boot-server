package com.example.boot.server.service;

import com.example.boot.server.pojo.dos.example.ExampleInfoDO;

import java.util.List;

/**
 * @author LiaoWei
 */
public interface ExampleInfoService {
    /**
     * 保存信息
     * @param exampleInfoDO 保存使用的实体类
     */
    void saveExampleInfo(ExampleInfoDO exampleInfoDO);

    /**
     * 更新信息
     * @param exampleInfoDO 更新使用的实体类
     */
    void updateExampleInfo(ExampleInfoDO exampleInfoDO);

    /**
     * 删除信息
     * @param id
     */
    void deleteExampleInfo(Integer id);

    /**
     * 查询信息
     * @param id
     * @return
     */
    ExampleInfoDO queryExampleInfoById(Integer id);

    /**
     * 查询信息列表
     * @param exampleInfoDO
     * @return
     */
    List<ExampleInfoDO> queryExampleInfoList(ExampleInfoDO exampleInfoDO);
}
