package com.chainxi.system.service.organization;

import cn.hutool.core.collection.CollUtil;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.system.convert.organization.SysDeptConvert;
import com.chainxi.system.convert.user.SysUserConvert;
import com.chainxi.system.entity.organization.SysDeptDo;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.mapper.organization.SysDeptMapper;
import com.chainxi.system.mapper.user.SysUserMapper;
import com.chainxi.system.reqvo.organization.DeptCreateReqVo;
import com.chainxi.system.reqvo.organization.DeptQueryReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateStatusReqVo;
import com.chainxi.system.respvo.organization.SysDeptDetailRespVo;
import com.chainxi.system.respvo.organization.SysDeptRespVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author : ChainXi
 * @Date : 2024/9/30 20:55
 * @Desc :
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl implements SysDeptService {
    private final SysDeptMapper sysDeptMapper;
    private final SysUserMapper sysUserMapper;

    @Nullable
    @Override
    public SysDeptDetailRespVo selectOneByDeptId(@Nonnull Long id) {
        SysDeptDo sysDeptDo = sysDeptMapper.selectById(id);
        if (sysDeptDo != null && sysDeptDo.getLeaderId() != null) {
            List<SysUserDo> sysUserDos =
                    sysUserMapper.selectUserIndexMapByIds(Collections.singleton(sysDeptDo.getLeaderId()));
            return SysDeptConvert.INSTANCE.convertDo2DetailVo(sysDeptDo,
                    !CollUtil.isEmpty(sysUserDos) ?
                    SysUserConvert.INSTANCE.convertDo2IndexMapVo(sysUserDos.get(0)) :
                    null);
        } else {
            return SysDeptConvert.INSTANCE.convertDo2DetailVo(sysDeptDo, null);
        }
    }

    @Nonnull
    @Override
    public List<SysDeptRespVo> selectList(@Nonnull DeptQueryReqVo reqVo) {
        List<SysDeptDo> sysDeptDos = sysDeptMapper.selectList(reqVo);
        Map<Long, String> userId2UserName = sysUserMapper
                .selectUserIndexMapByIds(sysDeptDos
                        .stream()
                        .map(SysDeptDo::getLeaderId)
                        .collect(Collectors.toSet()))
                .stream()
                .collect(Collectors.toMap(SysUserDo::getId, SysUserDo::getUserName));
        return sysDeptDos
                .stream()
                .map(e -> SysDeptConvert.INSTANCE.convertDo2Vo(e,
                        userId2UserName.get(e.getLeaderId())))
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public List<SysDeptDo> selectMapList() {
        return sysDeptMapper.selectDeptMapList();
    }

    @Nonnull
    @Override
    public Boolean createDept(@Nonnull DeptCreateReqVo reqVo) {
        List<Long> deptIds = sysDeptMapper.selectDeptIds();
        if (reqVo.getPid() == null) {
            reqVo.setPid(0L);
        }
        if (!deptIds.contains(reqVo.getPid())) {
            return Boolean.FALSE;
        }
        int mask = 0;
        while ((reqVo.getPid() >> mask) != 0) {
            mask += 8;
        }
        int finalMask = mask;
        List<Long> existIds = deptIds
                .stream()
                .filter(e -> (e >> finalMask) != 0 && (e & reqVo.getPid()) == reqVo.getPid())
                .toList();
        for (int i = 1; i < (1 << 8) - 1; i++) {
            if (!existIds.contains(((long) i << mask) + reqVo.getPid())) {
                SysDeptDo sysDeptDo = SysDeptConvert.INSTANCE.convertCreateReq2Do(reqVo);
                sysDeptDo.setId(((long) i << mask) + reqVo.getPid());
                return sysDeptMapper.insert(sysDeptDo) == 1;
            }
        }
        return Boolean.FALSE;
    }

    @Nonnull
    @Override
    public Boolean updateDept(@Nonnull DeptUpdateReqVo reqVo) {
        return sysDeptMapper.updateById(SysDeptConvert.INSTANCE.convertUpdateReq2Do(reqVo)) == 1;
    }

    @Nonnull
    @Override
    public Integer deleteDept(@Nonnull Long id) {
        sysUserMapper.revokeDeptId(id);
        return sysDeptMapper.deleteNode(id);
    }

    @Override
    public Integer updateDeptStatus(@Nonnull DeptUpdateStatusReqVo reqVo) {
        if (CommonStatusEnum.DISABLE.equals(reqVo.getStatus())) {
            sysUserMapper.revokeDeptId(reqVo.getId());
            return sysDeptMapper.disableNode(reqVo.getId());
        } else {
            return sysDeptMapper.updateById(SysDeptConvert.INSTANCE.convertUpdateReq2Do(reqVo));
        }
    }
}
