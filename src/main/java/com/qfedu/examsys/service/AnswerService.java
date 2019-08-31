package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Answer;

public interface AnswerService {

    //根据考试表Id和用户Id查询本次考试的成绩
    Answer selectAnswerByExamIdAndUserId(Integer eid,Integer uid);
}
