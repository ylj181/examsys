package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Exam;

import java.util.List;

public interface ExamService {

    //查询所有的考试信息列表
    List<Exam> findAllExams(Integer page,Integer limit);

    //根据考试表的Id查询对应的考试信息
    Exam findExamById(Integer id);
}
