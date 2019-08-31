package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Radio;

import java.util.List;

public interface RadioService {
    void insertMany (List<Radio> radios);

    // 查询所有选择题 ，分页展示
    List<Radio> findAllRadios(String name,Integer page, Integer limit);
}
