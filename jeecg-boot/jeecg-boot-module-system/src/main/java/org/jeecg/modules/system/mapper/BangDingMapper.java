package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.BangDingEntity;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Repository
public interface BangDingMapper extends BaseMapper<SysUser> {
    List<BangDingEntity> getBangDing(@Param("yonghu") String yonghu);
    void insertBangDing(@Param("bangDingEntity")BangDingEntity bangDingEntity);
    void deleteBangDing(@Param("bangDingEntity")BangDingEntity bangDingEntity);
}
