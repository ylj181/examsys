package com.qfedu.examsys.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qfedu.examsys.dao.ShortAnswerDao;
import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.ShortAnswer;
import com.qfedu.examsys.service.ShortAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortAnswerServiceImpl implements ShortAnswerService {

    @Autowired(required = false)
    private ShortAnswerDao shortAnswerDao;

    @Override
    public void insertManyShortAnswer(List<ShortAnswer> shortAnswers) {

        shortAnswerDao.insertManyShortAnswer(shortAnswers);

    }

    /**
     * 展示所有判断题
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Judge> findAllShortAnswers(String name, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        return shortAnswerDao.findAllShortAnswers(name);
    }

    @Override
    public ShortAnswer QueryShortAnswerById(Integer id) {
        return shortAnswerDao.QueryShortAnswerById(id);
    }

    @Override
    public void updateShortAnswerAnswer(ShortAnswer shortAnswer) {
        shortAnswerDao.updateShortAnswerAnswer(shortAnswer);
    }


    @Override
    public List<ShortAnswer> findAnythingShortAnswer(String name,Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return shortAnswerDao.findAnythingShortAnswer(name);
    }

    @Override
    public void updateRecentType(ShortAnswer shortAnswer) {
        shortAnswerDao.updateShortAnswerRecentType(shortAnswer);
    }

    @Override
    public void updateRecentTypeByIds(List<Integer> ids) {
        shortAnswerDao.updateRecentTypeByIds(ids);
    }
}
