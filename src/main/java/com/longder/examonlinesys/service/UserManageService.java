package com.longder.examonlinesys.service;




import com.longder.examonlinesys.entity.po.SysRole;
import com.longder.examonlinesys.entity.po.SysUser;

import java.util.List;

public interface UserManageService {
    /**
     * 学生列表
     * @return
     */
    List<SysUser> listStudent();

    /**
     * 教师列表
     * @return
     */
    List<SysUser> listTeacher();

    /**
     * 存储用户
     * @param sysUser 用户实体
     * @param sysRole 用户角色
     */
    void addUser(SysUser sysUser, SysRole sysRole);

    /**
     * 获取一个用户
     * @param userId
     * @return
     */
    SysUser getOneUser(Long userId);

    /**
     * 修改用户
     * @param sysUser
     */
    void updateUser(SysUser sysUser);

    /**
     * 根据登陆名查询用户的角色
     * @param loginName
     * @return
     */
    String getRoleNameByLoginName(String loginName);

    /**
     * 修改密码
     * @param newPassword
     */
    void changePassword(String newPassword);

}
