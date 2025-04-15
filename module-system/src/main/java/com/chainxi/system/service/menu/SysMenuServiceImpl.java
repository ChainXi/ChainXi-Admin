package com.chainxi.system.service.menu;

import cn.hutool.core.util.ObjectUtil;
import com.chainxi.common.web.exception.BizException;
import com.chainxi.system.constants.system.ErrorCodeConstants;
import com.chainxi.system.convert.auth.SysMenuConvert;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.mapper.menu.SysMenuMapper;
import com.chainxi.system.reqvo.menu.SysMenuListReqVo;
import com.chainxi.system.reqvo.menu.SysMenuUpdateReqVo;
import com.chainxi.system.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    private final AuthService authService;
    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuDo> getMenuList(SysMenuListReqVo reqVo) {
        if (ObjectUtil.isEmpty(reqVo)) {
            reqVo = new SysMenuListReqVo();
        }
        return sysMenuMapper.selectList(reqVo);
    }

    @Override
    public SysMenuDo getMenu(Long id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public Boolean saveMenu(SysMenuUpdateReqVo sysMenuUpdateReqVo) {
        SysMenuDo sysMenuDo = SysMenuConvert.INSTANCE.convertToMenuDo(sysMenuUpdateReqVo);
        if (sysMenuDo.getPid() != null) {
            List<SysMenuDo> sysMenuDos =
                    sysMenuMapper.selectListByMenuIds(Collections.singletonList(sysMenuDo.getPid()));
            if (!sysMenuDos.isEmpty()) {
                sysMenuDo.setRootId(sysMenuDos
                        .get(0)
                        .getRootId());
            }
            sysMenuMapper.insert(sysMenuDo);
        } else {
            sysMenuMapper.insert(sysMenuDo);
            sysMenuMapper.updateById(sysMenuDo.setRootId(sysMenuDo.getId()));
        }
        return true;
    }

    @Override
    public boolean updateMenu(SysMenuUpdateReqVo sysMenuUpdateReqVo) {
        sysMenuMapper.updateById(SysMenuConvert.INSTANCE.convertToMenuDo(sysMenuUpdateReqVo));
        return true;
    }

    @Override
    public void deleteMenu(Long id) {
        // 校验是否还有子菜单
        if (sysMenuMapper.selectCountByParentId(id) > 0) {
            throw new BizException(ErrorCodeConstants.MENU_EXISTS_CHILDREN);
        }
        // 校验删除的菜单是否存在
        if (sysMenuMapper.selectById(id) == null) {
            throw new BizException(ErrorCodeConstants.MENU_NOT_EXISTS);
        }
        sysMenuMapper.deleteById(id);
        // 删除授予给角色的权限
        authService.processMenuDeleted(id);
    }
}
