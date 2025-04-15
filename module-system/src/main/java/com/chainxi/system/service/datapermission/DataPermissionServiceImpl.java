package com.chainxi.system.service.datapermission;

import com.chainxi.system.convert.auth.DataPermissionConvert;
import com.chainxi.system.mapper.datapermission.DataPermissionDeptMatcherMapper;
import com.chainxi.system.reqvo.datapermission.DataPermissionDeptMatcherUpdateReqVo;
import com.chainxi.system.respvo.datapermission.DataPermissionDeptMatcherRespVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/11/21 0:17
 * @Desc :
 */
@Service
@RequiredArgsConstructor
public class DataPermissionServiceImpl implements DataPermissionService {
    private final DataPermissionDeptMatcherMapper deptMatcherMapper;

    @Nonnull
    @Override
    public DataPermissionDeptMatcherRespVo selectDeptMatchersByUserId(@Nonnull Long userId) {
        return DataPermissionConvert.INSTANCE.deptMatcherDos2RespVo(deptMatcherMapper.selectListByUserId(userId));
    }

    @Override
    public Boolean updateDeptMatcher(@Nonnull DataPermissionDeptMatcherUpdateReqVo reqVo) {
        deptMatcherMapper.deleteByUserId(reqVo.getUserId());
        deptMatcherMapper.insertBatch(DataPermissionConvert.INSTANCE.deptMatcherReqVo2Do(reqVo));
        return Boolean.TRUE;
    }

    @Override
    public Integer deleteDeptMatcherByUserId(@Nonnull Long userId){
        return deptMatcherMapper.deleteByUserId(userId);
    }
}
