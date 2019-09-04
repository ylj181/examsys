package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Permission;
import com.qfedu.examsys.pojo.RolePermission;

import java.util.List;

public interface RolePermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    //一级菜单
    List<Permission> findPermission(Integer rid);

    //二级菜单
    List<Permission> findPermission2(Integer rid);
}