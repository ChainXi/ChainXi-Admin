package com.chainxi.system.service.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.exception.BizException;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.utils.EncryptUtils;
import com.chainxi.system.config.datapermission.DeptDataPermissionRule;
import com.chainxi.system.constants.system.ErrorCodeConstants;
import com.chainxi.system.convert.user.SysUserConvert;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.mapper.user.SysUserMapper;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdatePasswordReqVo;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdateReqVo;
import com.chainxi.system.reqvo.user.user.*;
import com.chainxi.system.respvo.user.user.UserImportExcelVo;
import com.chainxi.system.respvo.user.user.UserImportRespVo;
import com.chainxi.system.service.auth.AuthService;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    @Value("${chainxi.sys.user-init-password:admin123}")
    private String userInitPassword;
    private final PasswordEncoder passwordEncoder;
    private final SysUserMapper sysUserMapper;
    private final AuthService authService;
    private final DeptDataPermissionRule deptDataPermissionRule;

    @Override
    public PageResult<SysUserDo> userPage(@Valid UserPageReqVo reqVo) {
        return sysUserMapper.selectUserPage(reqVo,deptDataPermissionRule);
    }

    @Override
    public PageResult<SysUserDo> userIndexMapPage(UserIndexMapPageReqVo reqVo) {
        return sysUserMapper.selectUserIndexMapPage(reqVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserCreateReqVo reqVo) {
        // 校验正确性
        validateUserForCreateOrUpdate(null,
                reqVo.getUserName(),
                reqVo.getPhoneNumber(),
                reqVo.getEmail());
        // 插入用户
        SysUserDo user = SysUserConvert.INSTANCE.convert(reqVo);
        user.setStatus(CommonStatusEnum.ENABLE); // 默认开启
        user.setPassword(encodePassword(reqVo.getPassword())); // 加密密码
        sysUserMapper.insert(user);
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserUpdateReqVo reqVo) {
        // 校验正确性
        validateUserForCreateOrUpdate(reqVo.getId(),
                reqVo.getUserName(),
                reqVo.getPhoneNumber(),
                reqVo.getEmail());
        // 更新用户
        SysUserDo updateObj = SysUserConvert.INSTANCE.convert(reqVo);
        sysUserMapper.updateById(updateObj);
    }


    @Override
    public void updateUserProfile(Long id, UserProfileUpdateReqVo reqVo) {
        // 校验正确性
        validateUserExists(id);
        validateEmailUnique(id, reqVo.getEmail());
        validateMobileUnique(id, reqVo.getPhoneNumber());
        // 执行更新
        SysUserDo sysUserDo = SysUserConvert.INSTANCE.convert(reqVo);
        sysUserDo.setId(id);
        sysUserMapper.updateById(sysUserDo);
    }

    @Override
    public void updateUserPassword(Long id, UserProfileUpdatePasswordReqVo reqVo) {
        PrivateKey privateKey = authService.queryKeyPair().getPrivate();
        String rawOldPassword = new String(EncryptUtils.decrypt(privateKey,
                Base64.getDecoder().decode(reqVo.getOldPassword())));
        // 校验旧密码密码
        validateOldPassword(id, rawOldPassword);
        authService.removeAuthUser(id);
        String rawNewPassword = new String(EncryptUtils.decrypt(privateKey,
                Base64.getDecoder().decode(reqVo.getNewPassword())));
        // 执行更新
        SysUserDo sysUserDo = new SysUserDo();
        sysUserDo.setId(id);
        sysUserDo.setPassword(encodePassword(rawNewPassword)); // 加密密码
        sysUserMapper.updateById(sysUserDo);
    }

    @Override

    public Boolean updateUserAvatar(Long id,String avatar) {
        validateUserExists(id);
        // 更新路径
        SysUserDo sysUserDO = new SysUserDo();
        sysUserDO.setId(id);
        sysUserDO.setAvatar(avatar);
        sysUserMapper.updateById(sysUserDO);
        return sysUserMapper.updateById(sysUserDO)==1;
    }

    @Override
    public void updateUserPassword(Long id, String password) {
        // 校验用户存在
        validateUserExists(id);
        authService.removeAuthUser(id);
        // 更新密码
        SysUserDo updateObj = new SysUserDo();
        updateObj.setId(id);
        updateObj.setPassword(encodePassword(password)); // 加密密码
        sysUserMapper.updateById(updateObj);
    }

    @Override
    public void updateUserStatus(Long id, CommonStatusEnum status) {
        // 校验用户存在
        validateUserExists(id);
        // 更新状态
        SysUserDo updateObj = new SysUserDo();
        updateObj.setId(id);
        updateObj.setStatus(status);
        sysUserMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        // 校验用户存在
        validateUserExists(id);
        // 删除用户
        sysUserMapper.deleteById(id);
        // 删除用户关联数据
        authService.processUserDeleted(id);
    }


    @Override
    public SysUserDo getUser(Long id) {
        return sysUserMapper.selectById(id);
    }


    @Override
    public List<SysUserDo> getUserList(UserExportReqVo reqVo) {
        return sysUserMapper.selectList(reqVo);
    }

    private void validateUserForCreateOrUpdate(Long id,
            String userName,
            String mobile,
            String email) {
        validateUserExists(id);
        // 校验用户名唯一
        validateUsernameUnique(id, userName);
        // 校验手机号唯一
        validateMobileUnique(id, mobile);
        // 校验邮箱唯一
        validateEmailUnique(id, email);
    }

    @VisibleForTesting
    void validateUserExists(Long id) {
        if (id == null) {
            return;
        }
        SysUserDo user = sysUserMapper.selectById(id);
        if (user == null) {
            throw new BizException(ErrorCodeConstants.USER_NOT_EXISTS);
        }
    }

    @VisibleForTesting
    void validateUsernameUnique(Long id, String userName) {
        if (StrUtil.isBlank(userName)) {
            return;
        }
        SysUserDo user = sysUserMapper.selectByUsername(userName);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw new BizException(ErrorCodeConstants.USER_USERNAME_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw new BizException(ErrorCodeConstants.USER_USERNAME_EXISTS);
        }
    }

    @VisibleForTesting
    void validateEmailUnique(Long id, String email) {
        if (StrUtil.isBlank(email)) {
            return;
        }
        SysUserDo user = sysUserMapper.selectByEmail(email);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw new BizException(ErrorCodeConstants.USER_EMAIL_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw new BizException(ErrorCodeConstants.USER_EMAIL_EXISTS);
        }
    }

    @VisibleForTesting
    void validateMobileUnique(Long id, String mobile) {
        if (StrUtil.isBlank(mobile)) {
            return;
        }
        SysUserDo user = sysUserMapper.selectByMobile(mobile);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw new BizException(ErrorCodeConstants.USER_MOBILE_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw new BizException(ErrorCodeConstants.USER_MOBILE_EXISTS);
        }
    }

    /**
     * 校验旧密码
     *
     * @param id          用户 id
     * @param oldPassword 旧密码
     */
    @VisibleForTesting
    void validateOldPassword(Long id, String oldPassword) {
        SysUserDo user = sysUserMapper.selectById(id);
        if (user == null) {
            throw new BizException(ErrorCodeConstants.USER_NOT_EXISTS);
        }
        if (!isPasswordMatch(oldPassword, user.getPassword())) {
            throw new BizException(ErrorCodeConstants.USER_PASSWORD_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 添加事务，异常则回滚所有导入
    public UserImportRespVo importUserList(List<UserImportExcelVo> importUsers,
            boolean isUpdateSupport) {
        if (CollUtil.isEmpty(importUsers)) {
            throw new BizException(ErrorCodeConstants.USER_IMPORT_LIST_IS_EMPTY);
        }
        UserImportRespVo respVO = new UserImportRespVo()
                .setCreateUsernames(new ArrayList<>())
                .setUpdateUsernames(new ArrayList<>())
                .setFailureUsernames(new LinkedHashMap<>());
        importUsers.forEach(importUser -> {
            // 校验，判断是否有不符合的原因
            try {
                validateUserForCreateOrUpdate(null,
                        null,
                        importUser.getPhoneNumber(),
                        importUser.getEmail());
            } catch (BizException ex) {
                respVO.getFailureUsernames().put(importUser.getUserName(), ex.getMessage());
                return;
            }
            // 判断如果不存在，在进行插入
            SysUserDo existUser = sysUserMapper.selectByUsername(importUser.getUserName());
            if (existUser == null) {
                SysUserDo sysUserDo = SysUserConvert.INSTANCE.convert(importUser);
                sysUserDo.setPassword(encodePassword(userInitPassword));
                sysUserMapper.insert(sysUserDo); // 设置默认密码
                respVO.getCreateUsernames().add(importUser.getUserName());
                return;
            }
            // 如果存在，判断是否允许更新
            if (!isUpdateSupport) {
                respVO
                        .getFailureUsernames()
                        .put(importUser.getUserName(),
                                ErrorCodeConstants.USER_USERNAME_EXISTS.getMsg());
                return;
            }
            SysUserDo updateUser = SysUserConvert.INSTANCE.convert(importUser);
            updateUser.setId(existUser.getId());
            sysUserMapper.updateById(updateUser);
            respVO.getUpdateUsernames().add(importUser.getUserName());
        });
        return respVO;
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}

