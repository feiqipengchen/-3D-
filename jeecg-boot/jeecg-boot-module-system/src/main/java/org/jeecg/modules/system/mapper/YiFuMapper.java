package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.SysUser;
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
public interface YiFuMapper extends BaseMapper<SysUser> {
    //获取件数小于11的衣服
    List<YiFuEntity> getYiFu();

    //获取筛选的衣服
    List<YiFuEntity> getYiFuSelect(@Param("yiFuEntity") YiFuEntity yiFuEntity);

    void insertYiFu(@Param("yiFuEntity") YiFuEntity yiFuEntity);

    void updateYiFu(@Param("yiFuEntity") YiFuEntity yiFuEntity);

    void updateYiFuJiaGe(@Param("yiFuEntity") YiFuEntity yiFuEntity);

    void deleteYiFu(@Param("yiFuEntity") YiFuEntity yiFuEntity);

    //获取衣服种类内的编号
    List<Integer> getYiFuSpecies(@Param("yifuzhonglei") String yifuzhonglei);


}
