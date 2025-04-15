package com.chainxi.system.config.datapermission;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.bo.DataPermissionDeptMatcherBo;
import com.chainxi.system.convert.auth.DataPermissionConvert;
import com.chainxi.system.entity.datapermission.DataPermissionDeptMatcherDo;
import com.chainxi.system.enums.auth.DataPermissionDeptMatchBucketType;
import com.chainxi.system.mapper.datapermission.DataPermissionDeptMatcherMapper;
import com.chainxi.system.utils.SecurityFrameworkUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Slf4j
public class DeptDataPermissionRule implements DataPermissionRule {
    private final DataPermissionDeptMatcherMapper deptMatcherMapper;

    @Override
    public <T, R, Children extends AbstractWrapper<T, R, Children>> AbstractWrapper<T, R,
            Children> set(AbstractWrapper<T, R, Children> wrapper, R column) {
        AuthUserBo loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser == null) {
            return wrapper.apply("null");
        }
        List<DataPermissionDeptMatcherDo> deptDataPermissions =
                deptMatcherMapper.selectListByUserId(loginUser.getUserId());
        if (deptDataPermissions == null || deptDataPermissions.isEmpty()) {
            return wrapper.apply("null");
        }
        DataPermissionDeptMatcherBo matcherBo =
                DataPermissionConvert.INSTANCE.deptMatcherDos2Bo(deptDataPermissions);
        Collection<Long> userDeptIds = loginUser.getDeptIds();
        DataPermissionDeptMatchBucketType matchType = matcherBo.getMatchType();
        Set<Long> deptIds = matcherBo.getDeptIds();
        if (DataPermissionDeptMatchBucketType.ALL.equals(matchType)) {
            return wrapper;
        }
        wrapper
                .apply("null")
                .or();
        if (DataPermissionDeptMatchBucketType.SUB_NODES.equals(matchType)) {
            userDeptIds.forEach(pid -> wrapper.ampersandEq(column, pid, pid));
        } else if (DataPermissionDeptMatchBucketType.CURRENT_NODE.equals(matchType)) {
            wrapper.inIfPresent(column, userDeptIds);
        }
        wrapper.or();
        deptIds.forEach(pid -> wrapper.ampersandEq(column, pid, pid));
        return wrapper;
    }


}
