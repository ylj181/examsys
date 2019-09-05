package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Answer;

import java.util.List;

public interface AnswerService {

    //根据考试表Id和用户Id查询本次考试的成绩
    Answer selectAnswerByExamIdAndUserId(Integer eid,Integer uid);

    /**
     *          通过 试卷ID 查询所有对应该试卷的答题信息
     *
     * @Author  imlee
     * @param eTid      试卷ID
     * @return          所有答题信息
     */
    public List<Answer> findAnswerListByETid(Integer eTid);

    /**
     *          阅卷后修改用户的成绩
     *
     * @Author  imlee
     * @param eTid
     * @param uid
     * @param score
     * @return
     */
    public Integer giveScore(Integer eTid, Integer uid, Integer score);

}
