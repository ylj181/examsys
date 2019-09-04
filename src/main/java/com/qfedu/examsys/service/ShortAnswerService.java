package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.ShortAnswer;

import java.util.List;

public interface ShortAnswerService {

    void insertManyShortAnswer(List<ShortAnswer> shortAnswers);

    public List<Judge> findAllShortAnswers(String name,Integer page, Integer limit);

    // 根据id查询
    ShortAnswer QueryShortAnswerById(Integer id);
    //修改答案
    void updateShortAnswerAnswer(ShortAnswer shortAnswer);

    // 总题库
    List<ShortAnswer> findAnythingShortAnswer(Integer page, Integer limit);

    // 修改 录入
    void updateRecentType(ShortAnswer shortAnswer);
    //  批量 录入
    void updateRecentTypeByIds(List<Integer> ids);
}
