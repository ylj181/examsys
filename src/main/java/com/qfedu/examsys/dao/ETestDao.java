package com.qfedu.examsys.dao;

import com.qfedu.examsys.pojo.ETest;
import org.apache.ibatis.annotations.Select;

public interface ETestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ETest record);

    int insertSelective(ETest record);

    ETest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ETest record);

    int updateByPrimaryKey(ETest record);

    @Select("select count(1) from eTest")
    int  getCount();

    //通过考场id  查询试卷 eTest
    ETest findByeid(Integer eid);
}
