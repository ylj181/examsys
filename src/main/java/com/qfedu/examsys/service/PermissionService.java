package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Permission;

import java.util.List;

/**
 * @Author Song
 * @Date 2019/9/515:26
 **/
public interface PermissionService {
    public Permission findByIdPermission(Integer id);

    public List<Permission> findByIdPermission2(Integer parentId);
}
