package com.qfedu.examsys.service.serviceImpl;/*
 *
 *   @Author ylj
 *   @Date   2019/9/5 20:39
 */

import com.github.pagehelper.PageHelper;
import com.qfedu.examsys.dao.VoDao;
import com.qfedu.examsys.pojo.Vo;
import com.qfedu.examsys.service.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoServiceImpl implements VoService {

    @Autowired(required = false)
    private VoDao voDao;

    @Override
    public List<Vo> findBySidAndEtId(Integer sid, Integer etid, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        return voDao.findBySidAndEtId(sid, etid);
    }
}
