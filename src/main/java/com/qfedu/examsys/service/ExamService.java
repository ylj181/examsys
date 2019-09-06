package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Exam;

import java.util.List;

public interface ExamService {

    //查询所有的考试信息列表
    List<Exam> findAllExams(Integer page,Integer limit);

    //根据考试表的Id查询对应的考试信息
    Exam findExamById(Integer id);

    //根据学科Id查询所有的考试信息
    List<Exam> findExamsBySubjectId(Integer subjectId,Integer page,Integer limit);

    // 添加考试
    void addExamInfo(Exam exam);
}
