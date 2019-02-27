package com.example.boot.server.controller;

import com.example.boot.server.exception.BootException;
import com.example.boot.server.form.AddForm;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dos.client.InfoDO;
import com.example.boot.server.pojo.dto.AddResultDTO;
import com.example.boot.server.pojo.dto.DeleteResultDTO;
import com.example.boot.server.pojo.dto.InfoQueryDTO;
import com.example.boot.server.pojo.vo.ResultVO;
import com.example.boot.server.service.AcctService;
import com.example.boot.server.service.ClientService;
import com.example.boot.server.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
public class ManageController {
    private final AcctService acctService;
    private final ClientService clientService;

    public ManageController(AcctService acctService, ClientService clientService) {
        this.acctService = acctService;
        this.clientService = clientService;
    }

    @PostMapping("/manage/add")
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<AddResultDTO> add(@Valid @RequestBody AddForm addForm) {
        String clientNo;
        String acctNo;
        InfoQueryDTO infoQueryDTO = new InfoQueryDTO();
        infoQueryDTO.setIdNo(addForm.getIdNo());
        // 查询身份证号对应的客户编号是否存在
        List<InfoDO> infoDOList = clientService.listComplex(infoQueryDTO);
        // 如果存在，则更新客户信息，如果不存在，则新建客户信息
        if (infoDOList.size() > 1) {
            throw new BootException("CLT0002");
        } else if (infoDOList.size() == 1) {
            InfoDO infoDO = new InfoDO();
            BeanUtils.copyProperties(addForm, infoDO);
            clientNo = infoDOList.get(0).getClientNo();
            infoDO.setClientNo(clientNo);
            clientService.updateClientInfo(infoDO);
        } else {
            InfoDO infoDO = new InfoDO();
            BeanUtils.copyProperties(addForm, infoDO);
            clientNo = clientService.saveClientInfo(infoDO);
        }
        // 新建账号
        MasterDO masterDO = new MasterDO();
        masterDO.setClientNo(clientNo);
        masterDO.setAcctStatus(Short.valueOf("0"));
        masterDO.setBalance(new BigDecimal("0"));
        masterDO.setRemark(addForm.getRemark());
        acctNo = acctService.saveAccount(masterDO);
        // 返回生成的账户编号和客户编号
        AddResultDTO addResultDTO = new AddResultDTO();
        addResultDTO.setAcctNo(acctNo);
        addResultDTO.setClientNo(clientNo);
        return ResultUtil.success(addResultDTO);
    }

    @DeleteMapping("/manage/delete/{idNo}")
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<DeleteResultDTO> delete(@PathVariable("idNo")  @NotBlank(message = "身份证编号不能为空") String idNo) {
        // 查询身份证号对应的客户编号是否存在
        InfoQueryDTO infoQueryDTO = new InfoQueryDTO();
        infoQueryDTO.setIdNo(idNo);
        List<InfoDO> infoDOList = clientService.listComplex(infoQueryDTO);
        if (infoDOList.size() > 1) {
            // 如果存在多条客户信息，则抛出异常
            throw new BootException("CLT0002");
        } else if (infoDOList.size() == 0) {
            // 如果不存在客户信息，则抛出异常
            throw new BootException("CLT0001");
        }
        // 删除客户信息
        String clientNo = infoDOList.get(0).getClientNo();
        clientService.deleteByClientNo(clientNo);
        // 查询客户号存在的账号是否存在,如果存在，则循环删除账号主要信息和详细信息
        List<String> deleteList = new ArrayList<>();
        DeleteResultDTO deleteResultDTO = new DeleteResultDTO();
        List<MasterDO> masterDOList = acctService.listMasterByClientNo(clientNo);
        masterDOList.forEach(masterDO -> {
            deleteList.add(masterDO.getAcctNo());
            acctService.removeAccount(masterDO.getAcctNo());
        });
        deleteResultDTO.setDeleteList(deleteList);
        return ResultUtil.success(deleteResultDTO);
    }
}
