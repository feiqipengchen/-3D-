package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.YiFuBiaoJiaEntity;
import org.jeecg.modules.system.entity.YiFuEntity;
import org.jeecg.modules.system.mapper.YiFuBiaoJiaMapper;
import org.jeecg.modules.system.mapper.YiFuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/yifu")
public class YiFuController {
    @Autowired
  private YiFuMapper yiFuMapper;
    @RequestMapping("/zong")
    public Result getYiFu() {
        Result<List<YiFuEntity>> result = new Result<List<YiFuEntity>>();
        result.setResult(yiFuMapper.getYiFu());
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/select")
    public Result getYiFuSelect(YiFuEntity yiFuEntity) {
        Result<List<YiFuEntity>> result = new Result<List<YiFuEntity>>();
        result.setResult(yiFuMapper.getYiFuSelect(yiFuEntity));
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/insert")
    public Result insertYiFu( YiFuEntity yiFuEntity) {
        Result<YiFuEntity> result = new Result<YiFuEntity>();
    yiFuMapper.insertYiFu(yiFuEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/update")
    public Result updateYiFu(YiFuEntity yiFuEntity) {
        Result<YiFuEntity> result = new Result<YiFuEntity>();
    yiFuMapper.updateYiFu(yiFuEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/updateJiaGe")
    public Result updateYiFuJiaGe(YiFuEntity yiFuEntity) {
        Result<YiFuEntity> result = new Result<YiFuEntity>();
        yiFuMapper.updateYiFuJiaGe(yiFuEntity);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/delete")
    public Result deleteYiFuJiaGe(YiFuEntity yiFuEntity) {
        Result<YiFuEntity> result = new Result<YiFuEntity>();
       yiFuMapper.deleteYiFu(yiFuEntity);
        result.setSuccess(true);
        return result;
    }
}
