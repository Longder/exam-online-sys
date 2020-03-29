package com.longder.examonlinesys.repository;


import com.longder.examonlinesys.entity.po.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole,Long> {

    /**
     * 查询用户下的角色
     * @param userId
     * @return
     */
    @Query("select ur from SysUserRole ur where ur.sysUser.id = :userId")
    List<SysUserRole> listRolesByUserId(@Param("userId") Long userId);
}
