package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.BangDingEntity;
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
public interface JueSeMapper extends BaseMapper<SysUser> {
    //插入新角色
    void insertJueSe(@Param("id") String id, @Param("jueSe") String jueSe);

    //查询用户id
    String selectJueSe(@Param("jueSe") String jueSe);

    //删除就角色
    void deleteJueSe(@Param("jueSe") String jueSe);
}
