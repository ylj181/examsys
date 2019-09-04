package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.Radio;

import java.util.List;

public interface RadioService {
    void insertMany (List<Radio> radios);

    // 查询所有选择题 ，分页展示
    List<Radio> findAllRadios(String name,Integer page, Integer limit);
    // 根据id查
    Radio QueryById(Integer id);
    // 修改答案
    void updateAnswer(Radio radio);
    // 总tiku
    List<Radio> findAnythingRadios(Integer page, Integer limit);
    // 修改 录入
    void updateRecentType(Radio radio);
    //  批量 录入
    void updateRecentTypeByIds(List<Integer> ids);



}
