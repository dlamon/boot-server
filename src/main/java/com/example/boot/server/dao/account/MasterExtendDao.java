package com.example.boot.server.dao.account;

import com.example.boot.server.pojo.dos.account.MasterDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiaoWei
 */
@Repository
public interface MasterExtendDao {
    List<MasterDO> selectAllByConditions();
}
