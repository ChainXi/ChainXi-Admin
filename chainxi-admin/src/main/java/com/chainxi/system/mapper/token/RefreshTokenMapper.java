package com.chainxi.system.mapper.token;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.token.RefreshTokenDo;
import com.chainxi.system.reqvo.token.TokenPageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RefreshTokenMapper extends BaseMapperX<RefreshTokenDo> {
    default int deleteByRefreshToken(String refreshToken) {
        return delete(getBaseQueryWrapper().eqIfPresent(RefreshTokenDo::getRefreshToken, refreshToken));
    }
    default int deleteByRefreshToken(List<String> refreshToken) {
        return delete(getBaseQueryWrapper().inIfPresent(RefreshTokenDo::getRefreshToken, refreshToken));
    }

    default int deleteByAccessToken(String accessToken) {
        return delete(getBaseQueryWrapper().eqIfPresent(RefreshTokenDo::getAccessToken, accessToken));
    }

    default List<RefreshTokenDo> selectClient(Long userId, Long did) {
        return selectList(getBaseQueryWrapper()
                .eqIfPresent(RefreshTokenDo::getUserId, userId)
                .eqIfPresent(RefreshTokenDo::getDid, did).select(RefreshTokenDo::getClientId,RefreshTokenDo::getAccessToken,RefreshTokenDo::getRefreshToken));
    }

    default int deleteBySso(Long userId, Long did) {
        return delete(getBaseQueryWrapper()
                .eq(RefreshTokenDo::getUserId, userId)
                .eq(RefreshTokenDo::getDid, did));
    }

    default RefreshTokenDo selectByAccessToken(String accessToken) {
        return selectOne(getBaseQueryWrapper().eq(RefreshTokenDo::getAccessToken, accessToken));
    }

    default RefreshTokenDo selectByRefreshToken(String refreshToken) {
        return selectOne(getBaseQueryWrapper().eq(RefreshTokenDo::getRefreshToken, refreshToken));
    }

    default PageResult<RefreshTokenDo> selectPage(TokenPageReqVO reqVo) {
        return selectPage(reqVo, getBaseQueryWrapper()
                .eqIfPresent(RefreshTokenDo::getUserId, reqVo.getUserId())
                .gt(RefreshTokenDo::getExpiresTime, LocalDateTime.now())
                .orderByDesc(RefreshTokenDo::getId));
    }

    default Integer removeAuthUser(Long userId){
        return delete(getBaseQueryWrapper().eq(RefreshTokenDo::getUserId,userId));
    }
}
