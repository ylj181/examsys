package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.User;

import java.util.List;

public interface UserService {
    public User login(String username);

    public void insert(User record);

    public List<User> findAllRole(Integer rid);

    public void updatePassword(User user);

    public void deleteByPrimaryKey(Integer id);

    //根据用户Id查询用户信息
    public User findUserById(Integer id);



}
