package com.qfedu.examsys.service;

/**
 * @Author Lei
 * @Date 2019-8-29 21:39
 */

/*
* 试卷信息
* */
public interface TestPaperService {

    //根据subjectId 获取题库  判断题 Judge 10道 单选题 Radio 10  ShortAnswer简单题 5道题

    String getpaperTest(Integer id);

}
