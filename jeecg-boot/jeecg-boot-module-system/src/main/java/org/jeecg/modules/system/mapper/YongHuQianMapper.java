package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.DianZanEntity;
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
public interface YongHuQianMapper extends BaseMapper<SysUser> {
    //获取用户钱包记录
    YongHuQianEntity getYongHuQian(@Param("yonghu") String yonghu);

    //插入用户钱包记录
    void insertYongHuQian(@Param("yongHuQianEntity") YongHuQianEntity yongHuQianEntity);

    //更新余额
    void updateYongHuQian(@Param("yongHuQianEntity") YongHuQianEntity yongHuQianEntity);
    //更新密码
    void updateYongHuQianPass(@Param("yongHuQianEntity") YongHuQianEntity yongHuQianEntity);
}
