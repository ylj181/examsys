package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Enroll;

import java.util.List;

public interface EnrollService {
    //向报名表中添加报名信息
    public int addEnrollInfo(Integer uid,Integer eid);

//    //通过报名表的id查询相应的报名信息
//    public Enroll selectEnrollById(Integer id);

    //查询指定学生所有的报名信息
    public List<Enroll> findAllEnroll(Integer uid,Integer page,Integer limit);
}
