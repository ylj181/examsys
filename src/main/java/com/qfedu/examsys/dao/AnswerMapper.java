package com.qfedu.examsys.dao;

import pojo.Answer;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}