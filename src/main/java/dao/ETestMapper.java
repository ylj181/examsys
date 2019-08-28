package dao;

import pojo.ETest;

public interface ETestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ETest record);

    int insertSelective(ETest record);

    ETest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ETest record);

    int updateByPrimaryKey(ETest record);
}