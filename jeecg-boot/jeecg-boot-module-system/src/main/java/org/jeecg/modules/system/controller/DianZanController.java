package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.UserCustomRecordEntity;
import org.jeecg.modules.system.mapper.DianZanMapper;
import org.jeecg.modules.system.mapper.UserCustomRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/dianzan")
public class DianZanController {
    @Autowired
    private DianZanMapper dianZanMapper;

    @RequestMapping("/select")
    public Result getDianZan(DianZanEntity dianZanEntity) {
        Result<DianZanEntity> result = new Result<DianZanEntity>();
        DianZanEntity temp = dianZanMapper.getDianZan(dianZanEntity);
        if (temp == null)
            dianZanMapper.insertDianZan(dianZanEntity);
        result.setResult(dianZanMapper.getDianZan(dianZanEntity));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/insert")
    public Result insertDianZan(DianZanEntity dianZanEntity) {
        Result<DianZanEntity> result = new Result<DianZanEntity>();
      dianZanMapper.insertDianZan(dianZanEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/update")
    public Result updateDianZan(DianZanEntity dianZanEntity) {
        Result<DianZanEntity> result = new Result<DianZanEntity>();
        dianZanMapper.updateDianZan(dianZanEntity);
        result.setSuccess(true);
        return result;
    }
}
