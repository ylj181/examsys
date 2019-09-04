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


    void updateJudgeAnswer(Judge judge);
    // id 查
    Judge QueryJudgeById(Integer id);
    // 总题库
    List<Judge> findAnythingJudges();

    void updateJudgeRecentType(Judge judge);
    // 批量录入 修改
    void updateRecentTypeByIds(List<Integer> ids);


}