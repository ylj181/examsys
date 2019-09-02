package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.ShortAnswer;

import java.util.List;

public interface ShortAnswerService {

    void insertManyShortAnswer(List<ShortAnswer> shortAnswers);

    public List<Judge> findAllShortAnswers(String name,Integer page, Integer limit);

}
