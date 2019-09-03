package com.qfedu.examsys.dao;


import com.qfedu.examsys.pojo.Radio;
import org.apache.ibatis.annotations.Param;

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

    // 查询所有选择题
    List<Radio> findAllRadios(@Param("name") String name);
    // 根据id查询
    Radio QueryById(Integer id);
    //修改答案
    void updateAnswer(Radio radio);
}