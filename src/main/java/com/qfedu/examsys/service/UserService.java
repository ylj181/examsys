package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.User;

import java.util.List;

public interface UserService {
    public User login(String username);

    public void insert(User record);

    public List<User> findAllRole(Integer rid,Integer page,Integer limit);

    public void updatePassword(User user);

    public void deleteByPrimaryKey(Integer id);

    //根据用户Id查询用户信息
    public User findUserById(Integer id);

    //批量删除
    public void deleteByIdUser(List<Integer> id);

    /**
     *          通过手机号码预注册
     *
     * @Author  imlee
     *
     * @param telephoneNumber
     * @return
     */
    public int signUpFirst(String telephoneNumber);

    /**
     *          补充用户名和密码
     *
     * @Author  imlee
     *
     * @param username
     * @param password
     * @param telephoneNumber
     * @return
     */
    public int signUp(String username, String password, String telephoneNumber);

    /**
     *          通过手机号码登录
     *
     * @Author  imlee
     *
     * @param telephoneNumber
     * @return
     */
    public User signInByTelephoneNumber(String telephoneNumber);

    /**
     *          通过手机号码重置密码
     *
     * @Author  imlee
     * @param password          新密码
     * @param telephoneNumber   手机号码
     * @return
     */
    public int resetPassword(String password, String telephoneNumber);

}
