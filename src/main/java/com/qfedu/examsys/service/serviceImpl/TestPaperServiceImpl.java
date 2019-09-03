package com.qfedu.examsys.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.dao.*;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.TestPaperService;
import com.qfedu.examsys.utils.SaveMapper;
import com.qfedu.examsys.utils.TestMapperUtils;
import com.qfedu.examsys.utils.TestTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
    private TestTypeDao testTypeDao;

    @Autowired(required = false)
    private SaveMapper saveMapper;

    @Autowired(required = false)
    private TestTypeUtils testTypeUtils;


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

        //试卷生成
        AllTestList allTestList = getTestMapper(1);
        //试卷保存为json格式
        saveMapper.saveMapper(allTestList, exam.getSubjectid(), exam.getId(), eTestName);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(1);
        jsonResult.setInfo(allTestList);
        return  jsonResult;
    }

    //生成学生测试使用的练习题 从eTest中随机抽取
    @Override
    public JsonResult getStudentTestMapper(TestType itemSelects ) {


     /*   private String p_name; //试卷名称
        private String p_duration;  // 考试时长
        private String p_section_names[];//章节名称
        // private  String  p_section_remarks[];  //单选或者多选或是判断
        private String p_dbids[]; // 题库名称
        private String p_qtypes[];//题型 判断/选择/简答
        private Integer p_levels[]; //难易级别
        private Integer p_qnums[];//试题数量*/

        itemSelects.setUid(1);


        //保存选择的试题
        HashMap<Object, Object> testmapperList = testTypeUtils.getTestmapperList(itemSelects);

        //获取添加eTest试题的ID主键
        //学生选择的试题类型保存

        String eTestId  =  testmapperList.get("eTestId").toString();


        itemSelects.seteTestId(eTestId);
        testTypeDao.insertSelective(itemSelects);

        JsonResult jsonResult =new JsonResult();

        //jsonResult.setInfo(testmapperList.get("allTesttLists"));

        jsonResult.setInfo(itemSelects.getId());
        jsonResult.setCode(1);

        return jsonResult;

    }

}
