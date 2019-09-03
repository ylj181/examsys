package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Subject;

import java.util.List;

public interface SubjectDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    //查询所有的学科
    List<Subject> findAllSubject();

    //根据学科Id查询学科信息
    Subject findBySubjectId(Integer id);

    List<Subject> findSub();

}