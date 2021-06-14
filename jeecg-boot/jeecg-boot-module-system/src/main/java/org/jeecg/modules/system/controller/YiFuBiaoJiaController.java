package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.YiFuBiaoJiaEntity;
import org.jeecg.modules.system.mapper.DianZanMapper;
import org.jeecg.modules.system.mapper.YiFuBiaoJiaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/yifubiaojia")
public class YiFuBiaoJiaController {
    @Autowired
   private YiFuBiaoJiaMapper yiFuBiaoJiaMapper;

    @RequestMapping("/select")
    public Result getYiFuBiaoJia(YiFuBiaoJiaEntity yiFuBiaoJiaEntity) {
        Result<Integer> result = new Result<Integer>();
        result.setResult(yiFuBiaoJiaMapper.getYiFuBiaoJia(yiFuBiaoJiaEntity));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/insert")
    public Result insertYiFuBiaoJia(YiFuBiaoJiaEntity yiFuBiaoJiaEntity) {
        Result<YiFuBiaoJiaEntity> result = new Result<YiFuBiaoJiaEntity>();
     yiFuBiaoJiaMapper.insertYiFuBiaoJia(yiFuBiaoJiaEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/update")
    public Result updateYiFuBiaoJia(YiFuBiaoJiaEntity yiFuBiaoJiaEntity) {
        Result<YiFuBiaoJiaEntity> result = new Result<YiFuBiaoJiaEntity>();
     yiFuBiaoJiaMapper.updateYiFuBiaoJia(yiFuBiaoJiaEntity);
        result.setSuccess(true);
        return result;
    }
}
