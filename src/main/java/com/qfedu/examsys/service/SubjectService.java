package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Subject;

import java.util.List;

public interface SubjectService {

    //查询所有的学科
    List<Subject> findAllSubject();

    public List<Subject> findSub();

}
