package com.example.boot.server.service.impl;

import com.example.boot.server.dao.client.InfoDao;
import com.example.boot.server.pojo.dos.client.InfoDO;
import com.example.boot.server.service.ClientService;
import org.springframework.stereotype.Service;

/**
 * @author LiaoWei
 */
@Service
public class ClientServiceImpl implements ClientService {
    private final InfoDao infoDao;

    public ClientServiceImpl(InfoDao infoDao) {
        this.infoDao = infoDao;
    }

    @Override
    public InfoDO getClientInfo(String clientNo) {
        return infoDao.selectByPrimaryKey(clientNo);
    }
}
