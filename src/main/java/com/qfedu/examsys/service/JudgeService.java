package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Judge;

import java.util.List;

public interface JudgeService {
    // 导入判断题
     void insertManyJudges(List<Judge> judges);

    List<Judge> findAllJudges(String name,Integer page, Integer limit);
    List<Judge> findAllJudgessss(String name);



    void updateJudgeAnswer(Judge judge);

    Judge QueryJudgeById(Integer id);
    // 总题库
    List<Judge> findAnythingJudge(Integer page, Integer limit);

    // 修改 录入
    void updateRecentType(Judge judge);
    //  批量 录入
    void updateRecentTypeByIds(List<Integer> ids);

}
