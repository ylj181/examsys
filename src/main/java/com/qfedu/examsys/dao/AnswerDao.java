package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Answer;

public interface AnswerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);

    //根据考试表Id和用户Id查询本次考试的成绩
    Answer selectAnswerByExamIdAndUserId(Integer eid,Integer uid);


}