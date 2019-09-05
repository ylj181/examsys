package com.qfedu.examsys.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qfedu.examsys.dao.EnrollDao;
import com.qfedu.examsys.pojo.Enroll;
import com.qfedu.examsys.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

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
    public int addEnrollInfo(Integer uid, Integer eid,Integer subjectid) {
        return enrollDao.addEnrollInfo(uid, eid,subjectid);
    }


//    /**
//     * 根据报名时报名表中生成的id,展示本次考试的报名信息
//     * @param id  报名表中生成的id
//     * @return  Enroll对象
//     */
//    @Override
//    public Enroll selectEnrollById(Integer id) {
//        return enrollDao.selectEnrollById(id);
//    }

    /**
     * 查询指定学生所有的报名信息
     * @return  该用户所有考试信息列表
     */
    @Override
    public List<Enroll> findAllEnroll(Integer uid,Integer page,Integer limit) {

        PageHelper.startPage(page,limit);

        List<Enroll> enrollList = enrollDao.findAllEnroll(uid);

        return enrollList;
    }

    /**
     * 老师查看所有报名信息
     * @param sid  学科Id
     * @param page
     * @param limit
     * @return  报名信息集合
     */
    @CrossOrigin
    @RequestMapping("/enroll/findAllEnrolls.do")
    @Override
    public List<Enroll> findAllEnrolls(Integer sid,Integer page,Integer limit) {
        PageHelper.startPage(page,limit);

        List<Enroll> allEnrolls = enrollDao.findAllEnrolls(sid);

        return allEnrolls;
    }

    /**
     * 根据用户Id、学科Id、报名时间查询报名信息
     * @param uid  用户Id
     * @param subjectId  学科Id
     * @param createtime  报名时间
     * @return  Enroll集合
     */
    @Override
    public List<Enroll> findEnrollsByUidAndSubjectIdAndCreatetime(Integer uid, Integer subjectId, Date createtime,Integer page,Integer limit) {

        PageHelper.startPage(page,limit);

        List<Enroll> enrolls = enrollDao.findEnrollsByUidAndSubjectIdAndCreatetime(uid, subjectId, createtime);

        return enrolls;
    }

}
