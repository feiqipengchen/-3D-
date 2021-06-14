package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.DianZanEntity;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.YanLunEntity;
import org.jeecg.modules.system.entity.YiFuEntity;
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
public interface YanLunMapper extends BaseMapper<SysUser> {
    List<YanLunEntity> getYanLun(@Param("yanLunEntity") YanLunEntity yanLunEntity);

    void insertYanLun(@Param("yanLunEntity") YanLunEntity yanLunEntity);

    //获取相应评分等级的个数
    Integer getYanLunXingJi(@Param("yanLunEntity") YanLunEntity yanLunEntity);

}
