package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.PermissionDao;
import com.qfedu.examsys.pojo.Permission;
import com.qfedu.examsys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Song
 * @Date 2019/9/515:27
 **/
@Service
public class PsermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    private PermissionDao permissionDao;

    @Override
    public Permission findByIdPermission(Integer id) {
        Permission permission = permissionDao.findByIdPermission( id );
        return permission;
    }

    @Override
    public List<Permission> findByIdPermission2(Integer parentId) {
        return permissionDao.findByIdPermission2( parentId );

    }

    @Override
    public void updatePerTea(Permission permission) {
         permissionDao.updatePerTea(permission);

    }

    @Override
    public void updatePerStu(Permission permission) {
        permissionDao.updatePerStu(permission);
    }

    @Override
    public void deletePer(Permission permission) {
        permissionDao.deletePer(permission);
    }

    @Override
    public List<Permission> findPerZero(Permission permission) {
        List<Permission> perZero = permissionDao.findPerZero(permission);

        return perZero;
    }
}
