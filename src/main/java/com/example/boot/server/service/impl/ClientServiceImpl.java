package com.example.boot.server.service.impl;

import com.example.boot.server.dao.client.InfoDao;
import com.example.boot.server.dao.client.extend.InfoExtendDao;
import com.example.boot.server.exception.BootException;
import com.example.boot.server.pojo.dos.client.InfoDO;
import com.example.boot.server.pojo.dto.InfoQueryDTO;
import com.example.boot.server.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author LiaoWei
 */
@Service
public class ClientServiceImpl implements ClientService {
    private final InfoDao infoDao;
    private final InfoExtendDao infoExtendDao;

    public ClientServiceImpl(InfoDao infoDao, InfoExtendDao infoExtendDao) {
        this.infoDao = infoDao;
        this.infoExtendDao = infoExtendDao;
    }

    @Override
    public String saveClientInfo(InfoDO infoDO) {
        StringBuilder sb = new StringBuilder();
        sb.append("119");
        Random rand = new Random();
        int length = 5;
        for(int i = 0; i < length; i++){
            sb.append(rand.nextInt(10));
        }
        String clientNo = sb.toString();
        infoDO.setClientNo(clientNo);
        int rows = infoDao.insertSelective(infoDO);
        if (rows != 1) {
            throw new BootException("CLT0003");
        }
        return clientNo;
    }

    @Override
    public void updateClientInfo(InfoDO info) {
        infoDao.updateByPrimaryKey(info);
    }

    @Override
    public InfoDO getClientInfo(String clientNo) {
        return infoDao.selectByPrimaryKey(clientNo);
    }

    @Override
    public void deleteByClientNo(String clientNo) {
        infoExtendDao.deleteByPrimaryKey(clientNo);
    }

    @Override
    public List<InfoDO> listComplex(InfoQueryDTO infoQueryDTO) {
        return infoExtendDao.selectAllByConditions(infoQueryDTO);
    }
}
