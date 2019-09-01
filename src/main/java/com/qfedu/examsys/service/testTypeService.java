package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.TestType;

/**
 * @Author Lei
 * @Date 2019-8-31 16:33
 */

public interface testTypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(TestType record);

    int insertSelective(TestType record);

    TestType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestType record);

    int updateByPrimaryKey(TestType record);
}
