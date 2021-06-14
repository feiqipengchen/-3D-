package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.CaiZhiEntity;
import org.jeecg.modules.system.entity.YiFuBiaoJiaEntity;
import org.jeecg.modules.system.entity.YiFuEntity;
import org.jeecg.modules.system.mapper.CaiZhiMapper;
import org.jeecg.modules.system.mapper.YiFuBiaoJiaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/caizhi")
public class CaiZhiController {
    @Autowired
    private CaiZhiMapper caiZhiMapper;

    @RequestMapping("/zong")
    public Result getCaiZhi() {
        Result<List<CaiZhiEntity>> result = new Result<List<CaiZhiEntity>>();
        result.setResult(caiZhiMapper.getCaiZhi());
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/select")
    public Result getCaiZhiSelect(CaiZhiEntity caiZhiEntity) {
        Result<CaiZhiEntity> result = new Result<CaiZhiEntity>();
        result.setResult(caiZhiMapper.getCaiZhiSelect(caiZhiEntity));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/insert")
    public Result insertCaiZhi(CaiZhiEntity caiZhiEntity) {
        Result<CaiZhiEntity> result = new Result<CaiZhiEntity>();
        caiZhiMapper.insertCaiZhi(caiZhiEntity);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/update")
    public Result updateCaiZhi(CaiZhiEntity caiZhiEntity) {
        Result<CaiZhiEntity> result = new Result<CaiZhiEntity>();
        caiZhiMapper.updateCaiZhi(caiZhiEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/updateJiaGe")
    public Result updateCaiZhiJiaGe(CaiZhiEntity caiZhiEntity) {
        Result<CaiZhiEntity> result = new Result<CaiZhiEntity>();
        caiZhiMapper.updateCaiZhiJiaGe(caiZhiEntity);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/delete")
    public Result deleteCaiZhi(CaiZhiEntity caiZhiEntity) {
        Result<CaiZhiEntity> result = new Result<CaiZhiEntity>();
        caiZhiMapper.deleteCaiZhi(caiZhiEntity);
        result.setSuccess(true);
        return result;
    }
}
