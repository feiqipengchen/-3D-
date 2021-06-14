package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.YongHuQianEntity;
import org.jeecg.modules.system.mapper.DianZanMapper;
import org.jeecg.modules.system.mapper.YiFuBiaoJiaMapper;
import org.jeecg.modules.system.mapper.YongHuQianMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/yonghuqian")
public class YongHuQianController {
    @Autowired
    private YongHuQianMapper yongHuQianMapper;

    @RequestMapping("/select")
    public Result<YongHuQianEntity> getYongHuQian(String yonghu) {
        Result<YongHuQianEntity> result = new Result<YongHuQianEntity>();
        result.setResult(yongHuQianMapper.getYongHuQian(yonghu));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/insert")
    public Result<YongHuQianEntity> insertYongHuQian(YongHuQianEntity yongHuQianEntity) {
        Result<YongHuQianEntity> result = new Result<YongHuQianEntity>();
        yongHuQianMapper.insertYongHuQian(yongHuQianEntity);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/update")
    public Result updateYongHuQian(YongHuQianEntity yongHuQianEntity) {
        Result<YongHuQianEntity> result = new Result<YongHuQianEntity>();
        yongHuQianMapper.updateYongHuQian(yongHuQianEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/updatepass")
    public Result updateYongHuQianPass(YongHuQianEntity yongHuQianEntity) {
        Result<YongHuQianEntity> result = new Result<YongHuQianEntity>();
        yongHuQianMapper.updateYongHuQianPass(yongHuQianEntity);
        result.setSuccess(true);
        return result;
    }
}
