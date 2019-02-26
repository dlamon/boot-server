package com.example.boot.server.service;

import com.example.boot.server.pojo.dos.client.InfoDO;
import com.example.boot.server.pojo.dto.InfoQueryDTO;

import java.util.List;

/**
 * @author LiaoWei
 */
public interface ClientService {

    /**
     * 新建客户信息
     * @param infoDO 客户信息
     * @return 生成的客户编号
     */
    String saveClientInfo(InfoDO infoDO);


    /**
     * 更新客户信息
     * @param infoDO 客户信息
     */
    void updateClientInfo(InfoDO infoDO);

    /**
     * 获取客户信息
     * @param clientNo 客户编号
     * @return 客户信息
     */
    InfoDO getClientInfo(String clientNo);

    /**
     * 通过客户编号删除客户信息
     * @param clientNo 客户编号
     */
    void deleteByClientNo(String clientNo);

    /**
     * 综合查询
     * @param infoQueryDTO 综合查询结构
     * @return 综合查询结果
     */
    List<InfoDO> listComplex(InfoQueryDTO infoQueryDTO);


}
