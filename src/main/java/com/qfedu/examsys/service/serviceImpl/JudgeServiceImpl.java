package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.JudgeDao;
import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JudgeServiceImpl implements JudgeService {
    @Autowired(required = false)
    private JudgeDao judgeDao;

    @Override
    public void insertManyJudges(List<Judge> judges) {
        judgeDao.insertManyJudges(judges);
    }
}
