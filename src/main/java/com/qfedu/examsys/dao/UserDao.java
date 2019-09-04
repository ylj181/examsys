package com.qfedu.examsys.dao;

import com.qfedu.examsys.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    //注册
    int insert(User record);

    int insertSelective(User record);

    //根据Id查user
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

    //批量删除
    void deleteByIdUser(List<Integer> id);

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
    public int signUp(@Param(value = "username") String username, @Param(value = "password") String password, @Param(value = "telephoneNumber") String telephoneNumber);

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
    public int resetPassword(@Param(value = "password") String password, @Param(value = "telephoneNumber") String telephoneNumber);
}