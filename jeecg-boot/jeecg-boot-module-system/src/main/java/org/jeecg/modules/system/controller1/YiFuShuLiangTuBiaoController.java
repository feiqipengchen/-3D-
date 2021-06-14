package org.jeecg.modules.system.controller1;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.YiFuEntity;
import org.jeecg.modules.system.mapper.YiFuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/yifushuliangtubiao")
public class YiFuShuLiangTuBiaoController {
    @Autowired
    private YiFuMapper yiFuMapper;

    @RequestMapping("/select")
    public Result<Integer[]> getCaiZhiSelect(YiFuEntity yiFuEntity) {
        Result<Integer[]> result = new Result<Integer[]>();
        Integer[] number = {0, 0, 0, 0, 0};
        List<YiFuEntity> temp = yiFuMapper.getYiFuSelect(yiFuEntity);
        if (temp == null || temp.isEmpty()) {
            result.setResult(number);
            result.setSuccess(true);
            return result;
        }
        for (int i = 0; i < temp.size(); i++) {
            switch (temp.get(i).getYifuxinghao()) {
                case "M":
                    number[0] = temp.get(i).getJianshu();
                    break;
                case "L":
                    number[1] = temp.get(i).getJianshu();
                    break;
                case "XL":
                    number[2] = temp.get(i).getJianshu();
                    break;
                case "2XL":
                    number[3] = temp.get(i).getJianshu();
                    break;
                case "3XL":
                    number[4] = temp.get(i).getJianshu();
                    break;
            }
        }
        result.setResult(number);
        result.setSuccess(true);
        return result;
    }

}
