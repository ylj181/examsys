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

    Permission findByIdPermission(Integer id);

    List<Permission> findByIdPermission2(Integer parentId);

    List<Permission> findAlls(Permission permission);

//    <!--    修改权限为教师所属-->
    void updatePerTea(Permission permission);

//    <!--    修改权限为学生所属-->
    void updatePerStu(Permission permission);

//    <!-- 修改 （删除）其权限( 隐藏为0不展示)
    void deletePer(Permission permission);

//    <!--    查看parentId为0的 功能 为添加准备-->

    List<Permission> findPerZero(Permission permission);

}
