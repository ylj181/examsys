package com.qfedu.examsys.service.serviceImpl;

import com.github.pagehelper.PageHelper;
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

    @Override
    public List<Judge> findAllJudges(String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return judgeDao.findAllJudges(name);
    }

    @Override
    public List<Judge> findAllJudgessss(String name) {
        return judgeDao.findAllJudges(name);
    }

    @Override
    public void updateJudgeAnswer(Judge judge) {
        judgeDao.updateJudgeAnswer(judge);
    }

    @Override
    public Judge QueryJudgeById(Integer id) {
        Judge judge = judgeDao.QueryJudgeById(id);
        return judge;
    }

    @Override
    public List<Judge> findAnythingJudge(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return judgeDao.findAnythingJudges();
    }

    @Override
    public void updateRecentType(Judge judge) {
        judgeDao.updateJudgeRecentType(judge);
    }

    @Override
    public void updateRecentTypeByIds(List<Integer> ids) {
        judgeDao.updateRecentTypeByIds(ids);
    }
}
