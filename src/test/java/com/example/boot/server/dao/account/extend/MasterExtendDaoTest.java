package com.example.boot.server.dao.account.extend;

import com.example.boot.server.dao.account.MasterExtendDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LiaoWei
 */
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@Transactional
@Slf4j
public class MasterExtendDaoTest {
    @Autowired
    MasterExtendDao masterExtendDao;

    @Test
    public void selectAllByConditions() {
        masterExtendDao.selectAllByConditions();
        /*
        List<MasterDO> masterDOList = masterExtendDao.selectAllByConditions();
        masterDOList.forEach(masterDO -> log.info("{}", masterDO));
        */
    }
}