package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.KaHaoQianEntity;
import org.jeecg.modules.system.entity.YongHuQianEntity;
import org.jeecg.modules.system.mapper.KaHaoQianMapper;
import org.jeecg.modules.system.mapper.YongHuQianMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/kahaoqian")
public class KaHaoQianController {
    @Autowired
    private KaHaoQianMapper kaHaoQianMapper;

    @RequestMapping("/select")
    public Result<KaHaoQianEntity> getKaHaoQian(String kahao){
        Result<KaHaoQianEntity> result = new Result<KaHaoQianEntity>();
        result.setResult(kaHaoQianMapper.getKaHaoQian(kahao));
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/update")
    public Result<KaHaoQianEntity> updateKaHaoQian(KaHaoQianEntity kaHaoQianEntity) {
        Result<KaHaoQianEntity> result = new Result<KaHaoQianEntity>();
       kaHaoQianMapper.updateKaHaoQian(kaHaoQianEntity);
        result.setSuccess(true);
        return result;
    }
}
