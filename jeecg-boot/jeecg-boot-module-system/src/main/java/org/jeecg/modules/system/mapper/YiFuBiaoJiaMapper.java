package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.YiFuBiaoJiaEntity;
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
public interface YiFuBiaoJiaMapper extends BaseMapper<SysUser> {
    Integer getYiFuBiaoJia(@Param("yiFuBiaoJiaEntity") YiFuBiaoJiaEntity yiFuBiaoJiaEntity);

    void insertYiFuBiaoJia(@Param("yiFuBiaoJiaEntity") YiFuBiaoJiaEntity yiFuBiaoJiaEntity);

    void updateYiFuBiaoJia(@Param("yiFuBiaoJiaEntity") YiFuBiaoJiaEntity yiFuBiaoJiaEntity);
}
