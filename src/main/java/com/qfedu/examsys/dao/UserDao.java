package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    //注册
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    //修改密码
    void updateByPrimaryKey(User user);

    //登录
    User login(String username);

    //根据角色Id查询
    List<User> findAllRole(Integer rid);

    //根据用户Id查询用户信息
    User findUserById(Integer id);



}