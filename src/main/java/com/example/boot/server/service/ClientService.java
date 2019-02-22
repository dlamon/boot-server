package com.example.boot.server.service;

import com.example.boot.server.pojo.dos.client.InfoDO;

/**
 * @author LiaoWei
 */
public interface ClientService {
    /**
     * 获取客户信息
     * @param clientNo 客户编号
     * @return 客户信息
     */
    InfoDO getClientInfo(String clientNo);
}
