package com.chainxi.system.convert.user;

import com.chainxi.system.bo.AuthRoleBo;
import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdatePasswordReqVo;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdateReqVo;
import com.chainxi.system.reqvo.user.user.UserCreateReqVo;
import com.chainxi.system.reqvo.user.user.UserUpdateReqVo;
import com.chainxi.system.respvo.user.profile.UserProfileRespVo;
import com.chainxi.system.respvo.user.user.UserExcelVo;
import com.chainxi.system.respvo.user.user.UserImportExcelVo;
import com.chainxi.system.respvo.user.user.UserIndexMapRespVo;
import com.chainxi.system.respvo.user.user.UserRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

@Mapper
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    UserRespVo convert(SysUserDo bean);

    SysUserDo convert(UserCreateReqVo bean);

    SysUserDo convert(UserUpdateReqVo bean);

    UserExcelVo convert02(SysUserDo bean);

    SysUserDo convert(UserImportExcelVo bean);

    UserProfileRespVo convert03(SysUserDo bean);

    List<UserProfileRespVo.Role> convertList(List<SysRoleDo> list);

    SysUserDo convert(UserProfileUpdateReqVo bean);

    SysUserDo convert(UserProfileUpdatePasswordReqVo bean);
    UserIndexMapRespVo convertDo2IndexMapVo(SysUserDo sysUserDo);

    default UserProfileRespVo convert(List<SysRoleDo> userRoles, SysUserDo userDo) {
        UserProfileRespVo userVO = convert03(userDo);
        userVO.setRoles(convertList(userRoles));
        return userVO;
    }

    AuthUserBo convertBo(SysUserDo userDo);

    default AuthUserBo convert(SysUserDo userDo, List<AuthRoleBo> authRoleBos) {
        AuthUserBo authUserBo = convertBo(userDo);
        authUserBo.setUserId(userDo.getId());
        authUserBo.setRoles(authRoleBos);
        authUserBo.setDeptIds(Collections.singletonList(userDo.getDeptId()));
        return authUserBo;
    }

}
