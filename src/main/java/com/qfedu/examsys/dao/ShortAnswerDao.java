package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.ShortAnswer;
import io.lettuce.core.dynamic.annotation.Param;

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

    //批量导入简答题
    void insertManyShortAnswer(List<ShortAnswer> shortAnswers);

    // 查询所有简答题
    List<Judge> findAllShortAnswers(@Param("name") String name);

}