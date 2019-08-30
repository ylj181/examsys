package com.qfedu.examsys.service.serviceImpl;

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
    public List<User> findAllRole(Integer rid) {
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

}
