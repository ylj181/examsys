package com.qfedu.examsys.dao;

import com.qfedu.examsys.pojo.ETest;

public interface ETestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ETest record);

    int insertSelective(ETest record);

    ETest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ETest record);

    int updateByPrimaryKey(ETest record);
}
