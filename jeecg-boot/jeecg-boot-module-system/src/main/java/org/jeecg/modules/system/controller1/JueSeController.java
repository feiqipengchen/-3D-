package org.jeecg.modules.system.controller1;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.CaiZhiEntity;
import org.jeecg.modules.system.mapper.CaiZhiMapper;
import org.jeecg.modules.system.mapper.JueSeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usercustomrecord/juese")
public class JueSeController {
    @Autowired
    JueSeMapper jueSeMapper;

    @RequestMapping("/juese")
    public Result<String> getCaiZhiSelect(String jueSe) {
        Result<String> result = new Result<String>();
        String id = jueSeMapper.selectJueSe(jueSe);
        jueSeMapper.deleteJueSe(id);
        String s = String.valueOf((int) (Math.random() * 10000));
        jueSeMapper.insertJueSe(s, id);
        result.setSuccess(true);
        return result;
    }

}
