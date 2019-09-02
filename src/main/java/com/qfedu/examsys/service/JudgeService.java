package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Judge;

import java.util.List;

public interface JudgeService {
    // 导入判断题
     void insertManyJudges(List<Judge> judges);

    List<Judge> findAllJudges(String name,Integer page, Integer limit);

    void updateJudgeAnswer(Judge judge);

    Judge QueryJudgeById(Integer id);

}
