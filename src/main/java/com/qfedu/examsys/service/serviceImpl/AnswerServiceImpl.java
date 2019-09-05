package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.AnswerDao;
import com.qfedu.examsys.pojo.Answer;
import com.qfedu.examsys.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired(required = false)
    private AnswerDao answerDao;


    /**
     * 根据考试表Id和用户Id查询本次考试的成绩
     * @param eid  考试表的Id
     * @param uid  用户的Id
     * @return  Answer对象
     */
    @Override
    public Answer selectAnswerByExamIdAndUserId(Integer eid,Integer uid) {
        return answerDao.selectAnswerByExamIdAndUserId(eid,uid);
    }

    @Override
    public List<Answer> findAnswerListByETid(Integer eTid) {
        return answerDao.findAnswerListByETid(eTid);
    }

    @Override
    public Integer giveScore(Integer eTid, Integer uid, Integer score) {
        return answerDao.giveScore(eTid, uid, score);
    }
}
