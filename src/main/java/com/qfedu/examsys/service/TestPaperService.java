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

    JsonResult getStudentExamMapper(Exam exam,String eTestName);

    //生成学生测试使用的练习题 从eTest中随机抽取

    JsonResult getStudentTestMapper();

    //保存学生考试答案

    void saveAnswer(Answer answer,Integer flag);

    // 判断学生练习题答案 正确个数

}
