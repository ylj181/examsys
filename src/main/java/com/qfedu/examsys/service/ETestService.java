package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.ETest;

/**
 * @Author Lei
 * @Date 2019-8-30 21:31
 */

public interface ETestService {
    int deleteByPrimaryKey(Integer id);

    int insert(ETest record);

    int insertSelective(ETest record);

    ETest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ETest record);

    int updateByPrimaryKey(ETest record);

}
