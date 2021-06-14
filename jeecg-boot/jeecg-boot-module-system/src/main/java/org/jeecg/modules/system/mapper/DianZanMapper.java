package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.SysUser;
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
public interface DianZanMapper extends BaseMapper<SysUser> {
    DianZanEntity getDianZan(@Param("dianZanEntity") DianZanEntity dianZanEntity);

    void insertDianZan(@Param("dianZanEntity") DianZanEntity dianZanEntity);

    void updateDianZan(@Param("dianZanEntity") DianZanEntity dianZanEntity);

    //<!--以下三个方法获取用户满意度的总数-->
    Integer getDianZanXiaoLian(@Param("dianZanEntity") DianZanEntity dianZanEntity);

    Integer getDianZanZhongLian(@Param("dianZanEntity") DianZanEntity dianZanEntity);

    Integer getDianZanKuLian(@Param("dianZanEntity") DianZanEntity dianZanEntity);
}
