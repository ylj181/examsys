package com.qfedu.examsys.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qfedu.examsys.dao.UserDao;
import com.qfedu.examsys.pojo.User;
import com.qfedu.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public User login(String username) {
        User user = userDao.login( username );
        return user;
    }

    @Override
    public void insert(User record) {
        userDao.insert( record );
    }

    @Override
    public List<User> findAllRole(Integer rid,Integer page,Integer limit) {
        PageHelper.startPage( page,limit );
        List<User> userList = userDao.findAllRole( rid );
        return userList;
    }

    @Override
    public void updatePassword(User user) {
        userDao.updateByPrimaryKey( user );
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        userDao.deleteByPrimaryKey( id );
    }

    /**
     * 根据用户Id查询用户信息
     * @param id 用户Id
     * @return  用户对象
     */
    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public void deleteByIdUser(List<Integer> id) {
        userDao.deleteByIdUser( id );
    }

    @Override
    public int signUpFirst(String telephoneNumber) {
        return userDao.signUpFirst(telephoneNumber);
    }

    @Override
    public int signUp(String username, String password, String telephoneNumber) {
        return userDao.signUp(username, password, telephoneNumber);
    }

    @Override
    public User signInByTelephoneNumber(String telephoneNumber) {
        return userDao.signInByTelephoneNumber(telephoneNumber);
    }

    @Override
    public int resetPassword(String password, String telephoneNumber) {
        return userDao.resetPassword(password, telephoneNumber);
    }

}
