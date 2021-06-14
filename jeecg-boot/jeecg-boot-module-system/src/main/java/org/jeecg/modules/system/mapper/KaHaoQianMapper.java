package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.KaHaoQianEntity;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.YongHuQianEntity;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Repository
public interface KaHaoQianMapper extends BaseMapper<SysUser> {
    //获取卡号信息
    KaHaoQianEntity getKaHaoQian(@Param("kahao") String kahao);

    //更新卡号余额
    void updateKaHaoQian(@Param("kaHaoQianEntity")KaHaoQianEntity kaHaoQianEntity);
}
