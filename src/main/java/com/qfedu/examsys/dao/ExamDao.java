package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Exam;

import java.util.List;

public interface ExamDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    //查询所有的考试信息列表
    List<Exam> findAllExams();

    //根据考试表的Id查询对应的考试信息
    Exam findExamById(Integer id);

    //根据学科Id查询所有的考试信息
    List<Exam> findExamsBySubjectId(Integer subjectId);

    //根据学科Id查询所有的考试信息
    List<Exam> findExamsBySubjectId2(Integer subjectId);

    void addExamInfo(Exam exam);


}