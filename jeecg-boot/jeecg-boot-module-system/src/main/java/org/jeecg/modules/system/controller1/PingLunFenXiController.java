package org.jeecg.modules.system.controller1;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.YanLunEntity;
import org.jeecg.modules.system.mapper.DianZanMapper;
import org.jeecg.modules.system.mapper.YanLunMapper;
import org.jeecg.modules.system.mapper.YiFuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/usercustomrecord/pinglunfenxi")
public class PingLunFenXiController {
    @Autowired
    private YanLunMapper yanLunMapper;
    @Autowired
    private DianZanMapper dianZanMapper;
    @Autowired
    private YiFuMapper yiFuMapper;

    @RequestMapping("/xingji")
    public Result<Integer[]> getUserCustomedSpeciesNumber(YanLunEntity yanLunEntity) {
        Result<Integer[]> result = new Result<Integer[]>();
        Integer[] number = {0, 0, 0, 0, 0};
        for (int i = 0; i < number.length; i++) {
            yanLunEntity.setPingfen(i + 1);
            number[i] = yanLunMapper.getYanLunXingJi(yanLunEntity);
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/xiaolian")
    public Result<Integer[][]>  getDianZanXiaoLian(DianZanEntity dianZanEntity) {
        Result<Integer[][]> result = new Result<Integer[][]>();
        List<Integer> temp= yiFuMapper.getYiFuSpecies(dianZanEntity.getYifuzhonglei());
        Integer[][] number =new Integer[2][temp.size()];
        for (int i = 0; i < temp.size(); i++) {
           number[0][i]=temp.get(i);
           dianZanEntity.setYifubianhao(number[0][i]);
           number[1][i]=dianZanMapper.getDianZanXiaoLian(dianZanEntity);
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/zhonglian")
    public Result<Integer[][]>  getDianZanZhongLian(DianZanEntity dianZanEntity) {
        Result<Integer[][]> result = new Result<Integer[][]>();
        List<Integer> temp= yiFuMapper.getYiFuSpecies(dianZanEntity.getYifuzhonglei());
        Integer[][] number =new Integer[2][temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            number[0][i]=temp.get(i);
            dianZanEntity.setYifubianhao(number[0][i]);
            number[1][i]=dianZanMapper.getDianZanZhongLian(dianZanEntity);
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }
    @RequestMapping("/kulian")
    public Result<Integer[][]>  getDianZanKuLian(DianZanEntity dianZanEntity) {
        Result<Integer[][]> result = new Result<Integer[][]>();
        List<Integer> temp= yiFuMapper.getYiFuSpecies(dianZanEntity.getYifuzhonglei());
        Integer[][] number =new Integer[2][temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            number[0][i]=temp.get(i);
            dianZanEntity.setYifubianhao(number[0][i]);
            number[1][i]=dianZanMapper.getDianZanKuLian(dianZanEntity);
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }
}
