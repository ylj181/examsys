package com.qfedu.examsys.dao;/*
 *
 *   @Author ylj
 *   @Date   2019/9/5 20:30
 */

import com.qfedu.examsys.pojo.Vo;

import java.util.List;

public interface VoDao {

    public List<Vo> findBySidAndEtId(Integer sid, Integer etid);
}
