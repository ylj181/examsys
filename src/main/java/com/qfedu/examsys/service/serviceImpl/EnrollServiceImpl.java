package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.EnrollDao;
import com.qfedu.examsys.pojo.Enroll;
import com.qfedu.examsys.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollServiceImpl implements EnrollService {

    @Autowired(required = false)
    private EnrollDao enrollDao;

    /**
     * 向报名表中添加报名信息
     * @param uid  报名的学生id
     * @param eid  所选考试表的id
     * @return  返回数据库受影响的行数
     */
    @Override
    public int addEnrollInfo(Integer uid, Integer eid) {
        return enrollDao.addEnrollInfo(uid, eid);
    }

    /**
     * 根据报名时报名表中生成的id,展示本次考试的报名信息
     * @param id  报名表中生成的id
     * @return  Enroll对象
     */
    @Override
    public Enroll selectEnrollById(Integer id) {
        return enrollDao.selectEnrollById(id);
    }
}
