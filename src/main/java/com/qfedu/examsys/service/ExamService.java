package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Exam;

import java.util.List;

public interface ExamService {

    //查询所有的考试信息列表
    List<Exam> findAllExams();
}
