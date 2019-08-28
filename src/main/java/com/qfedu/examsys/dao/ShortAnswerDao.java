package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.ShortAnswer;

public interface ShortAnswerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ShortAnswer record);

    int insertSelective(ShortAnswer record);

    ShortAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShortAnswer record);

    int updateByPrimaryKey(ShortAnswer record);
}