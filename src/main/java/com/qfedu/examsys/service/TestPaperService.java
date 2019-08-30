package com.qfedu.examsys.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qfedu.examsys.pojo.*;


/**
 * @Author Lei
 * @Date 2019-8-29 21:39
 */

/*
* 试卷信息
* */
public interface TestPaperService {

    //根据subjectId 获取题库  判断题 Judge 10道 单选题 Radio 10  ShortAnswer简单题 5道题

    AllTestList getTestMapper(Integer id);

    //保存随机生成的试卷 到Etest

    void saveStudentExamMapper(Exam exam,String eTestName);

    //保存学生的操作  ———— 练习  系统柜给出答案 并评判分数


}
