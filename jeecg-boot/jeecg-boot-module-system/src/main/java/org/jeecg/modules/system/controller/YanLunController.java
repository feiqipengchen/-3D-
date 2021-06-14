package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.YanLunEntity;
import org.jeecg.modules.system.mapper.DianZanMapper;
import org.jeecg.modules.system.mapper.YanLunMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/yanlun")
public class YanLunController {
    @Autowired
    private YanLunMapper yanLunMapper;

    @RequestMapping("/select")
    public Result getYanLun(YanLunEntity yanLunEntity) {
        Result<List<YanLunEntity>> result = new Result<List<YanLunEntity>>();
        result.setResult(yanLunMapper.getYanLun(yanLunEntity));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/insert")
    public Result insertYanLun(YanLunEntity yanLunEntity) {
        Result<List<YanLunEntity>> result = new Result<List<YanLunEntity>>();
       yanLunMapper.insertYanLun(yanLunEntity);
        result.setResult(yanLunMapper.getYanLun(yanLunEntity));
        result.setSuccess(true);
        return result;
    }

}
