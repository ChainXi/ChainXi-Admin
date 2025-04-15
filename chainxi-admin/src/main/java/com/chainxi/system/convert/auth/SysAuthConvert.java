package com.chainxi.system.convert.auth;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.entity.token.RefreshTokenDo;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.respvo.auth.TokenAccessRespVo;
import com.chainxi.system.respvo.auth.TokenRARespVo;
import com.chainxi.system.respvo.auth.TokenRespVo;
import com.chainxi.system.respvo.auth.SysAuthInfoRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface SysAuthConvert {
    SysAuthConvert INSTANCE = Mappers.getMapper(SysAuthConvert.class);

    default SysAuthInfoRespVo convert(SysUserDo user, Set<SysMenuDo> menuList) {
        return new SysAuthInfoRespVo()
                .setNickName(user.getNickName())
                .setAvatar(user.getAvatar())
                .setMenus(menuList);
    }

    TokenAccessRespVo convertAT(RefreshTokenDo refreshTokenDO);

    TokenRARespVo convertRAT(RefreshTokenDo refreshTokenDO);

    PageResult<TokenRespVo> convert2Page(PageResult<RefreshTokenDo> pageDo);

}
