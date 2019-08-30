package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.ShortAnswer;

import java.util.List;

public interface ShortAnswerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ShortAnswer record);

    int insertSelective(ShortAnswer record);

    ShortAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShortAnswer record);

    int updateByPrimaryKey(ShortAnswer record);

    //根据subjectId 返回shortAnswer集合
    List<ShortAnswer> findShortAnswers(Integer subjectId );
}