package org.jeecg.modules.system.controller1;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.CaiZhiEntity;
import org.jeecg.modules.system.entity.YiFuEntity;
import org.jeecg.modules.system.mapper.CaiZhiMapper;
import org.jeecg.modules.system.mapper.YiFuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/caizhishuliangtubiao")
public class caiZhiTuBiaoController {
    @Autowired
    private CaiZhiMapper caiZhiMapper;

    @RequestMapping("/select")
    public Result<Integer[][]> getCaiZhiSelect(Integer paixu) {
        Result<Integer[][]> result = new Result<Integer[][]>();
        Integer[][] number = new Integer[2][];
        List<CaiZhiEntity> temp = caiZhiMapper.getCaiZhi();
        number[0] = new Integer[temp.size()];
        number[1] = new Integer[temp.size()];
        if (paixu == 0) {
            for (int i = 0; i < temp.size(); i++) {
                number[0][i] = temp.get(i).getCaizhibianhao();
                number[1][i] = temp.get(i).getJianshu();
            }
        } else {
            for (int i = temp.size() - 1, j = 0; i >= 0; i--, j++) {
                number[0][j] = temp.get(i).getCaizhibianhao();
                number[1][j] = temp.get(i).getJianshu();
            }
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }

}
