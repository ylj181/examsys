package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.JudgeDao;
import com.qfedu.examsys.dao.RadioDao;
import com.qfedu.examsys.dao.ShortAnswerDao;
import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.Radio;
import com.qfedu.examsys.pojo.ShortAnswer;
import com.qfedu.examsys.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Lei
 * @Date 2019-8-29 21:40
 */

/**
 * 试卷信息
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {

    @Autowired(required = false)
    private RadioDao radioDao;

    @Autowired(required = false)
    private JudgeDao judgeDao;

    @Autowired(required = false)
    private ShortAnswerDao shortAnswerdao;


    //根据subjectId 获取题库  判断题 Judge 10道 单选题 Radio 10  ShortAnswer简单题 5道题
    @Override
    public String getpaperTest(Integer id) {

        List<Radio> radios = radioDao.findRadios(id);

        //List<Judge> judges = judgeDao.findJudges(id);

       // List<ShortAnswer> shortAnswers = shortAnswerdao.findShortAnswers(id);

        //随机获取各科题库
        List<Radio> radioList = new ArrayList<>(); // 准备radio 随机题
        List<Judge> judgeList = new ArrayList<>(); // 准备Judge 随机题
        List<ShortAnswer> answerList = new ArrayList<>(); // 准备ShortAnswes 随机题

        //判断随机数是否多次出现
        StringBuffer sb = new StringBuffer();

        //随机获取radios 10道题
        if(radios.size()>5) {
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int i1 = random.nextInt(radios.size()-1);
                if(!sb.toString().contains(String.valueOf(i1))){
                    sb.append(i1);
                    radioList.add(radios.get(i1));

                   continue;
                }
                i--;
            }

        }

        JsonResult jsonResult = new JsonResult();



        return null;
    }
}
