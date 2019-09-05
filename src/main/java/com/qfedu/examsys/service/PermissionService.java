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

    //    <!--    修改权限为教师所属-->
    void updatePerTea(Permission permission);

    //    <!--    修改权限为学生所属-->
    void updatePerStu(Permission permission);

    //    <!-- 修改 （删除）其权限( 隐藏为0不展示)
    void deletePer(Permission permission);

//    <!--    查看parentId为0的 功能 为添加准备-->

    List<Permission> findPerZero(Permission permission);

}
