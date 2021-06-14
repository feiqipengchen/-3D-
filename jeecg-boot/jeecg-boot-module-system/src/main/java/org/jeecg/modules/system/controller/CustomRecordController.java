package org.jeecg.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.UserCustomRecordEntity;
import org.jeecg.modules.system.mapper.UserCustomRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/usercustomrecord")
public class CustomRecordController {
    @Autowired
    private UserCustomRecordMapper userCustomRecordMapper;
    //用户定制记录
    @RequestMapping("/user")
    public Result<List<UserCustomRecordEntity>> getUserCustomRecord(String dingdanren){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        result.setResult(userCustomRecordMapper.getUserCustomRecord(dingdanren));
        result.setSuccess(true);
        return  result;
    }
    @RequestMapping("/getUserCustomRecordSelect")
    public Result<List<UserCustomRecordEntity>>getUserCustomRecordSelect(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        result.setResult(userCustomRecordMapper.getUserCustomRecordSelect(userCustomRecordEntity));
        result.setSuccess(true);
        return  result;
    }
    //用户定制中
    @RequestMapping("/getUserCustoming")
    public Result<List<UserCustomRecordEntity>>getUserCustoming(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        result.setResult(userCustomRecordMapper.getUserCustoming(userCustomRecordEntity));
        result.setSuccess(true);
        return  result;
    }
    @RequestMapping("/getUserCustomingSelect")
    public Result<List<UserCustomRecordEntity>>getUserCustomingSelect(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        result.setResult(userCustomRecordMapper.getUserCustomingSelect(userCustomRecordEntity));
        result.setSuccess(true);
        return  result;
    }
    @RequestMapping("/updateUserCustomingSelect")
    public Result<List<UserCustomRecordEntity>>updateUserCustomingSelect(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
     userCustomRecordMapper.updateUserCustomingSelect(userCustomRecordEntity);
        result.setSuccess(true);
        return  result;
    }
    //更新
    @RequestMapping("/updateUserCustomingSelect1")
    public Result<List<UserCustomRecordEntity>>updateUserCustomingSelect1(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        userCustomRecordMapper.updateUserCustomingSelect1(userCustomRecordEntity);
        result.setSuccess(true);
        return  result;
    }
    //用户未定值
    @RequestMapping("/getUserUnCustom")
    public Result<List<UserCustomRecordEntity>>getUserUnCustom(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        result.setResult(userCustomRecordMapper.getUserUnCustom(userCustomRecordEntity));
        result.setSuccess(true);
        return  result;
    }
    @RequestMapping("/getUserUnCustomSelect")
    public Result<List<UserCustomRecordEntity>>getUserUnCustomSelect(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        result.setResult(userCustomRecordMapper.getUserUnCustomSelect(userCustomRecordEntity));
        result.setSuccess(true);
        return  result;
    }
    @RequestMapping("/updateUserUnCustomSelect")
    public Result<List<UserCustomRecordEntity>>updateUserUnCustomSelect(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        userCustomRecordMapper.updateUserUnCustomSelect(userCustomRecordEntity);
        result.setSuccess(true);
        return  result;
    }
    @RequestMapping("/updateUserUnCustomSelect1")
    public Result<List<UserCustomRecordEntity>>updateUserUnCustomSelect1(UserCustomRecordEntity userCustomRecordEntity){
        Result<List<UserCustomRecordEntity>> result = new Result<List<UserCustomRecordEntity>>();
        userCustomRecordMapper.updateUserUnCustomSelect1(userCustomRecordEntity);
        result.setSuccess(true);
        return  result;
    }
}
