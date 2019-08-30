package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Enroll;

import java.util.List;


public interface EnrollDao {

    //向报名表中添加报名信息
    public int addEnrollInfo(Integer uid,Integer eid);

//    //根据报名时报名表中生成的id,展示本次考试的报名信息
//    public Enroll selectEnrollById(Integer id);

    //查询指定学生所有的考试信息
    public List<Enroll> findAllEnroll(Integer uid);

}
