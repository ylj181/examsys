package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Radio;

public interface RadioDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Radio record);

    int insertSelective(Radio record);

    Radio selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Radio record);

    int updateByPrimaryKey(Radio record);
}