package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserDepart;
import org.jeecg.modules.system.entity.UserCustomRecordEntity;
import org.jeecg.modules.system.model.SysUserSysDepartModel;
import org.jeecg.modules.system.vo.SysUserDepVo;
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
public interface UserCustomRecordMapper extends BaseMapper<SysUser> {
    //用户定制记录
    List<UserCustomRecordEntity> getUserCustomRecord(@Param("dingdanren") String dingdanren);

    List<UserCustomRecordEntity> getUserCustomRecordSelect(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    //用户定制中
    List<UserCustomRecordEntity> getUserCustoming(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    List<UserCustomRecordEntity> getUserCustomingSelect(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    void updateUserCustomingSelect(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    //更新
    void updateUserCustomingSelect1(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    //用户未定值
    List<UserCustomRecordEntity> getUserUnCustom(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    List<UserCustomRecordEntity> getUserUnCustomSelect(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    void updateUserUnCustomSelect(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    //更新
    void updateUserUnCustomSelect1(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);

    //用户购买某一种类件数或管理员
    Integer getUserCustomedSpeciesNumber(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);
 //<!--用户购买某一种类件数管理员-->
    Integer getUserCustomedSizeNumber(@Param("userCustomRecordEntity") UserCustomRecordEntity userCustomRecordEntity);
}
