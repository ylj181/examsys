package dao;

import pojo.ShortAnswer;

public interface ShortAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShortAnswer record);

    int insertSelective(ShortAnswer record);

    ShortAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShortAnswer record);

    int updateByPrimaryKey(ShortAnswer record);
}