package com.chainxi.system.mapper.user;

import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.config.datapermission.DataPermissionRule;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.reqvo.user.user.UserExportReqVo;
import com.chainxi.system.reqvo.user.user.UserIndexMapPageReqVo;
import com.chainxi.system.reqvo.user.user.UserPageReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapperX<SysUserDo> {
    default PageResult<SysUserDo> selectUserPage(UserPageReqVo reqVo, DataPermissionRule rule) {
        return selectPage(reqVo, rule.set(getBaseQueryWrapper()
                .likeIfPresent(SysUserDo::getUserName, reqVo.getUserName())
                .likeIfPresent(SysUserDo::getPhoneNumber, reqVo.getPhoneNumber())
                .eqIfPresent(SysUserDo::getStatus, reqVo.getStatus())
                .betweenIfPresent(SysUserDo::getCreateTime, reqVo.getCreateTime())
                .orderByDesc(SysUserDo::getId),SysUserDo::getDeptId)
        );
    }

    default PageResult<SysUserDo> selectUserIndexMapPage(UserIndexMapPageReqVo reqVo) {
        return selectPage(reqVo, getBaseQueryWrapper()
                .and(reqVo.getName() != null, wrapper -> wrapper
                        .like(SysUserDo::getUserName, reqVo.getName())
                        .or()
                        .like(SysUserDo::getNickName, reqVo.getName()))
                .eqIfPresent(SysUserDo::getStatus, CommonStatusEnum.ENABLE)
                .orderByDesc(SysUserDo::getId)
                .select(SysUserDo::getId, SysUserDo::getUserName, SysUserDo::getNickName));
    }

    default List<SysUserDo> selectUserIndexMapByIds(Collection<Long> userIds) {
        return selectList(getBaseQueryWrapper()
                .inIfPresent(SysUserDo::getId, userIds)
                .select(SysUserDo::getId, SysUserDo::getUserName));
    }

    default SysUserDo selectByUsername(String userName) {
        return selectOne(getBaseQueryWrapper().eq(SysUserDo::getUserName, userName));
    }

    default SysUserDo selectByEmail(String email) {
        return selectOne(getBaseQueryWrapper().eq(SysUserDo::getEmail, email));
    }

    default SysUserDo selectByMobile(String mobile) {
        return selectOne(getBaseQueryWrapper().eq(SysUserDo::getPhoneNumber, mobile));
    }

    default List<SysUserDo> selectList(UserExportReqVo reqVo) {
        return selectList(getBaseQueryWrapper()
                .likeIfPresent(SysUserDo::getUserName, reqVo.getUserName())
                .likeIfPresent(SysUserDo::getPhoneNumber, reqVo.getPhoneNumber())
                .eqIfPresent(SysUserDo::getStatus, reqVo.getStatus())
                .betweenIfPresent(SysUserDo::getCreateTime, reqVo.getCreateTime()));
    }

    default void revokeDeptId(Long pid) {
        update(getBaseUpdateWrapper()
                .ampersandEq(SysUserDo::getDeptId, pid, pid)
                .set(SysUserDo::getDeptId, null));
    }

}

