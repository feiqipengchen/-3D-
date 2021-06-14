package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.CaiZhiEntity;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.YiFuBiaoJiaEntity;
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
public interface CaiZhiMapper extends BaseMapper<SysUser> {
    List<CaiZhiEntity> getCaiZhi();

    CaiZhiEntity getCaiZhiSelect(@Param("caiZhiEntity") CaiZhiEntity caiZhiEntity);

    void insertCaiZhi(@Param("caiZhiEntity") CaiZhiEntity caiZhiEntity);

    void updateCaiZhi(@Param("caiZhiEntity") CaiZhiEntity caiZhiEntity);

    void updateCaiZhiJiaGe(@Param("caiZhiEntity") CaiZhiEntity caiZhiEntity);

    void deleteCaiZhi(@Param("caiZhiEntity") CaiZhiEntity caiZhiEntity);
}
