package com.chainxi.system.component;

import com.chainxi.system.bo.AuthRoleBo;
import com.chainxi.system.convert.auth.SysRoleConvert;
import com.chainxi.system.convert.user.SysUserConvert;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.mapper.user.SysUserMapper;
import com.chainxi.system.mapper.user.SysUserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUserDo sysUserDo = sysUserMapper.selectByUsername(userName);
        //如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(sysUserDo)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<AuthRoleBo> rolesByUserId =
                SysRoleConvert.INSTANCE.convertToAuthRole(sysUserRoleMapper.getRolesByUserId((sysUserDo.getId())));
        //封装成UserDetails对象返回
        return SysUserConvert.INSTANCE.convert(sysUserDo, rolesByUserId);
    }
}

