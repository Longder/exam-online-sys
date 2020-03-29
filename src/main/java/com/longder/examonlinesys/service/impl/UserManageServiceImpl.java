package com.longder.examonlinesys.service.impl;


import com.longder.examonlinesys.entity.po.SysRole;
import com.longder.examonlinesys.entity.po.SysUser;
import com.longder.examonlinesys.entity.po.SysUserRole;
import com.longder.examonlinesys.repository.SysUserRepository;
import com.longder.examonlinesys.repository.SysUserRoleRepository;
import com.longder.examonlinesys.security.SecurityUtil;
import com.longder.examonlinesys.service.UserManageService;
import com.longder.examonlinesys.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManageServiceImpl implements UserManageService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    /**
     * 默认密码
     */
    @Value("${system.default-password}")
    private String defaultPassword;

    @Override
    public List<SysUser> listStudent() {
        List<SysUser> allUsers = sysUserRepository.findAll();
        return allUsers.stream()
                .filter(sysUser -> sysUser.getRoles().get(0).getRole().equals(SysRole.ROLE_STUDENT))
                .collect(Collectors.toList());
    }

    public List<SysUser> listTeacher(){
        List<SysUser> allUsers = sysUserRepository.findAll();
        return allUsers.stream()
                .filter(sysUser -> sysUser.getRoles().get(0).getRole().equals(SysRole.ROLE_TEACHER))
                .collect(Collectors.toList());
    }
    /**
     * 存储用户
     *
     * @param sysUser 用户实体
     * @param sysRole 用户角色
     */
    @Override
    @Transactional
    public void addUser(SysUser sysUser, SysRole sysRole) {
        sysUser.setPassword(EncryptionUtil.encrypt(defaultPassword));
        sysUserRepository.save(sysUser);
        SysUserRole userRole = new SysUserRole();
        userRole.setSysUser(sysUser);
        userRole.setRole(sysRole);
        sysUserRoleRepository.save(userRole);
    }

    /**
     * 获取一个用户
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser getOneUser(Long userId) {
        return sysUserRepository.getOne(userId);
    }

    /**
     * 修改用户
     *
     * @param sysUser
     */
    @Override
    @Transactional
    public void updateUser(SysUser sysUser) {
        SysUser dbUser = sysUserRepository.getOne(sysUser.getId());
        dbUser.setLoginName(sysUser.getLoginName());
        dbUser.setName(sysUser.getName());
        sysUserRepository.save(dbUser);
    }


    /**
     * 根据用户名查询用户的角色
     *
     * @param loginName
     * @return
     */
    @Override
    public String getRoleNameByLoginName(String loginName) {
        SysUser sysUser = sysUserRepository.getByLoginName(loginName);
        if(ObjectUtils.isEmpty(sysUser)){
            return "no";
        }else{
            List<SysUserRole> userRoleList = sysUserRoleRepository.listRolesByUserId(sysUser.getId());
            SysUserRole sysUserRole = userRoleList.get(0);
            if(ObjectUtils.isEmpty(sysUserRole)){
                return "no";
            }else{
                return sysUserRole.getRole().getName();
            }
        }
    }

    /**
     * 修改密码
     *
     * @param newPassword
     */
    @Override
    @Transactional
    public void changePassword(String newPassword) {
        SysUser sysUser = SecurityUtil.getCurrentUser();
        assert sysUser != null;
        sysUser.setPassword(EncryptionUtil.encrypt(newPassword.trim()));
        sysUserRepository.save(sysUser);
    }
}
