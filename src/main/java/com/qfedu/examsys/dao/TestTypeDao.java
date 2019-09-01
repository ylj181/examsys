package com.qfedu.examsys.dao;

import com.qfedu.examsys.pojo.TestType;

public interface TestTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TestType record);

    int insertSelective(TestType record);

    TestType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestType record);

    int updateByPrimaryKey(TestType record);
}