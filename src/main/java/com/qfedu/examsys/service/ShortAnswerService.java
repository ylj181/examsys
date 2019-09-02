package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.ShortAnswer;
import com.sun.org.apache.xpath.internal.operations.Lt;

import java.util.List;

public interface ShortAnswerService {

    void insertManyShortAnswer(List<ShortAnswer> shortAnswers);

    public List<Judge> findAllShortAnswers(String name,Integer page, Integer limit);

}
