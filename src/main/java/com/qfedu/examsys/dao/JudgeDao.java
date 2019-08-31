package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Judge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JudgeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Judge record);

    int insertSelective(Judge record);

    Judge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Judge record);

    int updateByPrimaryKey(Judge record);

    //根据subjectId 返回Juede集合
    List<Judge> findJudges(Integer subjectId );

    // 导入判断题
    void insertManyJudges(List<Judge> judges);

    List<Judge> findAllJudges(@Param("name") String name);
}