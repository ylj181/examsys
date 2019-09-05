package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Permission;

import java.util.List;

public interface PermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> findAlls(Permission permission);

}