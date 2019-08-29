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

    int updateByPrimaryKey(User record);

    //登录
    User login(String username);

    //根据角色Id查询
    List<User> findAllRole(Integer rid);


}