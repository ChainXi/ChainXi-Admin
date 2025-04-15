package com.chainxi.system.service.user;

import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.user.SysUserDo;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdatePasswordReqVo;
import com.chainxi.system.reqvo.user.profile.UserProfileUpdateReqVo;
import com.chainxi.system.reqvo.user.user.*;
import com.chainxi.system.respvo.user.user.UserImportExcelVo;
import com.chainxi.system.respvo.user.user.UserImportRespVo;

import jakarta.validation.Valid;
import java.util.List;

public interface SysUserService {

    PageResult<SysUserDo> userPage(@Valid UserPageReqVo reqVo);
    PageResult<SysUserDo> userIndexMapPage(@Valid UserIndexMapPageReqVo reqVo);

    /**
     * 创建用户
     *
     * @param reqVo 用户信息
     * @return 用户编号
     */
    Long createUser(@Valid UserCreateReqVo reqVo);

    /**
     * 修改用户
     *
     * @param reqVo 用户信息
     */
    void updateUser(@Valid UserUpdateReqVo reqVo);

    /**
     * 修改用户个人信息
     *
     * @param id    用户编号
     * @param reqVo 用户个人信息
     */
    void updateUserProfile(Long id, @Valid UserProfileUpdateReqVo reqVo);

    /**
     * 修改用户个人密码
     *
     * @param id    用户编号
     * @param reqVo 更新用户个人密码
     */
    void updateUserPassword(Long id, @Valid UserProfileUpdatePasswordReqVo reqVo);

    /**
     * 更新用户头像
     *
     * @param id 用户 id
     */
    Boolean updateUserAvatar(Long id,String avatar);

    /**
     * 修改密码
     *
     * @param id       用户编号
     * @param password 密码
     */
    void updateUserPassword(Long id, String password);

    /**
     * 修改状态
     *
     * @param id     用户编号
     * @param status 状态
     */
    void updateUserStatus(Long id, CommonStatusEnum status);

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    void deleteUser(Long id);

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    SysUserDo getUser(Long id);

    /**
     * 获得用户列表
     *
     * @param reqVo 列表请求
     * @return 用户列表
     */
    List<SysUserDo> getUserList(UserExportReqVo reqVo);

    /**
     * 批量导入用户
     *
     * @param importUsers     导入用户列表
     * @param isUpdateSupport 是否支持更新
     * @return 导入结果
     */
    UserImportRespVo importUserList(List<UserImportExcelVo> importUsers, boolean isUpdateSupport);

    /**
     * 判断密码是否匹配
     *
     * @param rawPassword     未加密的密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    boolean isPasswordMatch(String rawPassword, String encodedPassword);

}
