package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.BangDingEntity;
import org.jeecg.modules.system.entity.YanLunEntity;
import org.jeecg.modules.system.mapper.BangDingMapper;
import org.jeecg.modules.system.mapper.YanLunMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/bangding")
public class BangDingController {
    @Autowired
    private BangDingMapper bangDingMapper;

    @RequestMapping("/select")
    public Result<List<BangDingEntity>> getBangDing(String yonghu) {
        Result<List<BangDingEntity>> result = new Result<List<BangDingEntity>>();
        result.setResult(bangDingMapper.getBangDing(yonghu));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/insert")
    public Result<List<BangDingEntity>> insertBangDing(BangDingEntity bangDingEntity) {
        Result<List<BangDingEntity>> result = new Result<List<BangDingEntity>>();
      bangDingMapper.insertBangDing(bangDingEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/delete")
    public Result<List<BangDingEntity>> deleteBangDing(BangDingEntity bangDingEntity) {
        Result<List<BangDingEntity>> result = new Result<List<BangDingEntity>>();
        bangDingMapper.deleteBangDing(bangDingEntity);
        result.setSuccess(true);
        return result;
    }
}
