package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.PermissionDao;
import com.qfedu.examsys.dao.RolePermissionDao;
import com.qfedu.examsys.pojo.Permission;
import com.qfedu.examsys.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Song
 * @Date 2019/9/48:52
 **/
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired(required = false)
    private RolePermissionDao rolePermissionDao;

    @Autowired(required = false)
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findPermission(Integer rid) {
        List<Permission> permissionList = rolePermissionDao.findPermission( rid );
        return permissionList;
    }

    @Override
    public List<Permission> findPermission2(Integer rid) {
        List<Permission> permission2 = rolePermissionDao.findPermission2( rid );
        return permission2;
    }

    @Override
    public List<Permission> findAlls(Permission permission) {
        return permissionDao.findAlls(permission);
    }
}
