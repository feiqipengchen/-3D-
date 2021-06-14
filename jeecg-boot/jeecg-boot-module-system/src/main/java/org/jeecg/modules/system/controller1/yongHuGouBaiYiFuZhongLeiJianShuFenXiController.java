package org.jeecg.modules.system.controller1;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.CaiZhiEntity;
import org.jeecg.modules.system.entity.UserCustomRecordEntity;
import org.jeecg.modules.system.mapper.CaiZhiMapper;
import org.jeecg.modules.system.mapper.UserCustomRecordMapper;
import org.jeecg.modules.system.mapper.YiFuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/yonghugoumaiyifuzhongleijianshu")
public class yongHuGouBaiYiFuZhongLeiJianShuFenXiController {
    @Autowired
    private UserCustomRecordMapper userCustomRecordMapper;
    @Autowired
    private YiFuMapper yiFuMapper;

    //管理员用户查询
    @RequestMapping("/jianshufenxi")
    public Result<Integer[]> getUserCustomedSpeciesNumber(UserCustomRecordEntity userCustomRecordEntity) {
        Result<Integer[]> result = new Result<Integer[]>();
        Integer[] number = {0, 0, 0, 0, 0};
        String[] yiFuZhongLei = {"shangyi", "kuzi", "xiezi", "quanshen", "peishi"};
        for (int i = 0; i < yiFuZhongLei.length; i++) {
            userCustomRecordEntity.setYifuzhonglei(yiFuZhongLei[i]);
            number[i] = userCustomRecordMapper.getUserCustomedSpeciesNumber(userCustomRecordEntity);
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("/sizejianshufenxi")
    public Result<Integer[]> getUserCustomedSizeNumber(UserCustomRecordEntity userCustomRecordEntity) {
        Result<Integer[]> result = new Result<Integer[]>();
        Integer[] number = {0, 0, 0, 0, 0};
        String[] size = {"M", "L", "XL", "2XL", "3XL"};
        for (int i = 0; i < size.length; i++) {
            userCustomRecordEntity.setYifuxinghao(size[i]);
            number[i] = userCustomRecordMapper.getUserCustomedSizeNumber(userCustomRecordEntity);
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }

    //管理员衣服种类内销售分析
    @RequestMapping("/zhongleineijianshufenxi")
    public Result<Integer[][]> getUserCustomedBianHaoNumber(UserCustomRecordEntity userCustomRecordEntity) {
        Result<Integer[][]> result = new Result<Integer[][]>();
        List<Integer> temp = yiFuMapper.getYiFuSpecies(userCustomRecordEntity.getYifuzhonglei());
        Integer[][] number = new Integer[2][temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            number[0][i] = temp.get(i);
            userCustomRecordEntity.setCaizhibainhao(number[0][i]);
            number[1][i] = userCustomRecordMapper.getUserCustomedSizeNumber(userCustomRecordEntity);
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }
}
