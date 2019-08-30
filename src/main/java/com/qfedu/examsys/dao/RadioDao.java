package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Radio;

import java.util.List;

public interface RadioDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Radio record);

    int insertSelective(Radio record);

    Radio selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Radio record);

    int updateByPrimaryKey(Radio record);
    // 导入选择题
    void insertMany (List<Radio> radios);

    //根据subjectId  返回Radio集合
    List<Radio> findRadios(Integer subjectId);
}