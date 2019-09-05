package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Permission;

import java.util.List;

/**
 * @Author Song
 * @Date 2019/9/48:49
 **/
public interface RolePermissionService {
    public List<Permission> findPermission(Integer rid);

    public List<Permission> findPermission2(Integer rid);

    public List<Permission> findAlls(Permission permission);

}
