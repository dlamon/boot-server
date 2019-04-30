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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/query")
@Api(tags ="/query", description = "查询账户")
public class QueryController {
    private final AcctService acctService;
    private final ClientService clientService;

    public QueryController(AcctService acctService, ClientService clientService) {
        this.acctService = acctService;
        this.clientService = clientService;
    }

    @GetMapping("/master")
    @ApiOperation("查询主要信息")
    public ResultVO<MasterDO> getMaster(@RequestParam("acctNo") @NotBlank(message = "账户编号不能为空") @ApiParam("账号") String acctNo) {
        MasterDO masterDO = acctService.getMasterInfo(acctNo);
        return ResultUtil.success(masterDO);
    }

    @GetMapping("/detail")
    public ResultVO<List<DetailDO>> getDetail(@NotBlank(message = "账户编号不能为空") String acctNo) {
        List<DetailDO> detailDOList = acctService.listDetailInfo(acctNo);
        return ResultUtil.success(detailDOList);
    }

    @GetMapping("/client/{clientNo}")
    public ResultVO<InfoDO> getClient(@PathVariable("clientNo") @NotBlank(message = "客户编号不能为空") String clientNo) {
        InfoDO infoDO = clientService.getClientInfo(clientNo);
        return ResultUtil.success(infoDO);
    }

    @GetMapping("/listComplex")
    public ResultVO<ComplexResultDTO> listComplex(AcctQueryDTO acctQueryDTO) {
        ComplexResultDTO complexResultDTO = new ComplexResultDTO();
        AcctResultDTO acctResultDTO = acctService.listComplex(acctQueryDTO);
        if (acctResultDTO == null) {
            throw new BootException("ACT0002");
        }

        BeanUtils.copyProperties(acctResultDTO, complexResultDTO);
        complexResultDTO.setAcctCreateTime(acctResultDTO.getCreateTime());
        complexResultDTO.setAcctUpdateTime(acctResultDTO.getUpdateTime());

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
