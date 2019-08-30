package com.qfedu.examsys.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.dao.*;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.TestPaperService;
import com.qfedu.examsys.utils.TestMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private TestMapperUtils mapperUtils;


    @Autowired(required = false)
    private RadioDao radioDao;

    @Autowired(required = false)
    private JudgeDao judgeDao;

    @Autowired(required = false)
    private ShortAnswerDao shortAnswerdao;

    @Autowired(required = false)
    private ETestDao eTestDao;

    @Autowired(required = false)
    private AnswerDao answerDao;


    //根据subjectId 获取题库  判断题 Judge 10道 单选题 Radio 10  ShortAnswer简单题 5道题
    @Override
    public  AllTestList  getTestMapper(Integer id) {

        List<Radio> radios = radioDao.findRadios(id);

        List<Judge> judges = judgeDao.findJudges(id);

        List<ShortAnswer> shortAnswers = shortAnswerdao.findShortAnswers(id);

        //随机获取各科题库
        List<Radio> radioList =null; // 准备radio 随机题
        List<Judge> judgeList = null; // 准备Judge 随机题
        List<ShortAnswer> shortAnswerList = null; // 准备ShortAnswes 随机题

        //随机获取radios 10道题
        radioList = mapperUtils.getRandomList(radios, 10);

        // 随机获取 judges 10道题
        judgeList = mapperUtils.getRandomList(judges, 10);

        // 随机获取 shortAnswers 5道题

        shortAnswerList = mapperUtils.getRandomList(shortAnswers,5);

       // JsonResult jsonResult = new JsonResult();

        AllTestList allTestList  = new AllTestList(radioList,judgeList,shortAnswerList);

        return allTestList;
    }
    //生成考试卷子 并保存到eTest表 ————考试 需要讲师阅卷
    @Override
    public JsonResult getStudentExamMapper(Exam exam,String eTestName)  {
        AllTestList allTestList = getTestMapper(1);

        ObjectMapper objectMapper = new ObjectMapper();

        ETest eTest = new ETest();

        String radioStr = null;
        String answerStr =null;
        String  judgeStr= null;
        try {
            radioStr = objectMapper.writeValueAsString(allTestList.getRadioList());

            answerStr  = objectMapper.writeValueAsString(allTestList.getAnswerList());

            judgeStr = objectMapper.writeValueAsString(allTestList.getJudgeList());


            //关联的exam
            eTest.setEid(1);

            //考卷名称
            eTest.setName(eTestName);

            //考卷所属科目
           // eTest.setSubjectid(exam.getSubjectid());
            eTest.setSubjectid(1);

            eTest.setJudgejson(judgeStr);

            eTest.setRadiojson(radioStr);

            eTest.setShortanswerjson(answerStr);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        eTestDao.insertSelective(eTest);

        JsonResult jsonResult = new JsonResult();

        jsonResult.setInfo(eTest);

        jsonResult.setCode(1);

        return  jsonResult;
    }

    //生成学生测试使用的练习题 从eTest中随机抽取
    @Override
    public JsonResult getStudentTestMapper() {

        //获取eTest coutn()个数

        int count = eTestDao.getCount();

        Random random = new Random();

        int i = random.nextInt(count);

        ETest eTest = eTestDao.selectByPrimaryKey(i);

        JsonResult jsonResult = new JsonResult();

        jsonResult.setCode(1);

        jsonResult.setInfo(eTest);

        return  jsonResult;
    }

    //  练习为 1  考试为 0
    @Override
    public void saveAnswer(Answer answer,Integer flag) {

        if (flag==1){

            answerDao.insertSelective(answer);

        }else {


        }

    }

}
