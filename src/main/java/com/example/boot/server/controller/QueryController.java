package com.example.boot.server.controller;

import com.example.boot.server.exception.BootException;
import com.example.boot.server.pojo.dos.account.DetailDO;
import com.example.boot.server.pojo.dos.account.MasterDO;
import com.example.boot.server.pojo.dos.client.InfoDO;
import com.example.boot.server.pojo.dto.AcctQueryDTO;
import com.example.boot.server.pojo.dto.AcctResultDTO;
import com.example.boot.server.pojo.dto.ComplexResultDTO;
import com.example.boot.server.pojo.vo.ResultVO;
import com.example.boot.server.service.AcctService;
import com.example.boot.server.service.ClientService;
import com.example.boot.server.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Validated
public class QueryController {
    private final AcctService acctService;
    private final ClientService clientService;

    public QueryController(AcctService acctService, ClientService clientService) {
        this.acctService = acctService;
        this.clientService = clientService;
    }

    @GetMapping("query/master")
    public ResultVO<MasterDO> getMaster(@RequestParam("acctNo") @NotBlank(message = "账户编号不能为空") String acctNo) {
        MasterDO masterDO = acctService.getMasterInfo(acctNo);
        return ResultUtil.success(masterDO);
    }

    @GetMapping("query/detail")
    public ResultVO<List<DetailDO>> getDetail(@NotBlank(message = "账户编号不能为空") String acctNo) {
        List<DetailDO> detailDOList = acctService.listDetailInfo(acctNo);
        return ResultUtil.success(detailDOList);
    }

    @GetMapping("query/client/{clientNo}")
    public ResultVO<InfoDO> getClient(@PathVariable("clientNo") @NotBlank(message = "客户编号不能为空") String clientNo) {
        InfoDO infoDO = clientService.getClientInfo(clientNo);
        return ResultUtil.success(infoDO);
    }

    @GetMapping("/query/listComplex")
    public ResultVO<ComplexResultDTO> listComplex(AcctQueryDTO acctQueryDTO) {
        ComplexResultDTO complexResultDTO = new ComplexResultDTO();
        AcctResultDTO acctResultDTO = acctService.listComplex(acctQueryDTO);
        if (acctResultDTO != null) {
            BeanUtils.copyProperties(acctResultDTO, complexResultDTO);
            complexResultDTO.setAcctCreateTime(acctResultDTO.getCreateTime());
            complexResultDTO.setAcctUpdateTime(acctResultDTO.getUpdateTime());
        }

        InfoDO infoDO = clientService.getClientInfo(acctResultDTO.getClientNo());
        if (infoDO == null) {
            throw new BootException("CLT0001");
        }
        BeanUtils.copyProperties(infoDO, complexResultDTO);
        complexResultDTO.setClientCreateTime(infoDO.getCreateTime());
        complexResultDTO.setClientUpdateTime(infoDO.getUpdateTime());
        return ResultUtil.success(complexResultDTO);
    }
}
