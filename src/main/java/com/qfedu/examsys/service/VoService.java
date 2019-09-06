package com.qfedu.examsys.service;/*
 *
 *   @Author ylj
 *   @Date   2019/9/5 20:38
 */

import com.qfedu.examsys.pojo.Vo;

import java.util.List;

public interface VoService {

    public List<Vo> findBySidAndEtId(Integer sid, Integer etid, Integer page, Integer limit);
}
