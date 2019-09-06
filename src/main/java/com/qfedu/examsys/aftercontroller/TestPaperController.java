package com.qfedu.examsys.aftercontroller;
import com.github.pagehelper.Page;
import com.qfedu.examsys.dao.*;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.ETestService;
import com.qfedu.examsys.service.TestPaperService;
import com.qfedu.examsys.service.testTypeService;
import com.qfedu.examsys.utils.AnswerUtils;
import com.qfedu.examsys.utils.WriteReadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 * @Author Lei
 * @Date 2019-8-29 22:11
 */
@Controller
public class TestPaperController {

    /**
     * 根据前台发送的sucjectId 获取该科目的试题
     * @return
     */

    @Autowired
    private TestPaperService testPaperService;

    @Autowired(required = false)
    private TestTypeDao testTypeDao;

    @Autowired(required = false)
    private testTypeService testTypeService;

    @Autowired(required = false)
    private AnswerUtils answerUtils;

    @Autowired(required = false)
    private ETestDao  eTestDao;

    @Autowired
    private WriteReadJson writeReadJson;

    @Autowired(required = false)
    private RadioDao radioDao;

    @Autowired(required = false)
    private JudgeDao judgeDao;

    @Autowired
    private ETestService eTestService;

    @Autowired(required = false)
    private EnrollDao enrollDao;

    @Autowired(required = false)
    private ExamDao examDao;


    //试卷信息生成 并保存到eTest  需要管理员开启exam 考场 Exam exam  设置考卷名称
    //正常code 为1 返回etest对象
    @CrossOrigin
    @RequestMapping("/getExamMapper.do")
    @ResponseBody
    // eId
    // sId
    //eName
    ////127.0.0.1:8020/examsys/ExamMapper.html?eId=5&sId=1&eName=测试&uId=1
    public JsonResult getPaperTest(Integer eId,Integer sId,String eName,Integer uId) throws IOException {

        JsonResult jsonResult = new JsonResult();

        //试题存在 则返回已经存在的试题
        Exam exam = new Exam();
        exam.setId(eId);
        exam.setSubjectid(sId);

        ETest byeid = eTestDao.findByeid(exam.getId());


        if(byeid==null){//没有就生成该考场的试卷
            jsonResult  = testPaperService.getStudentExamMapper(exam, eName);

            byeid=eTestDao.findByeid(exam.getId());
        }

        boolean flag =false;
        if(byeid!=null){
            //判断用户是否报名了该场考试
            List<Enroll> allEnroll = enrollDao.findAllEnroll(uId);
            for (Enroll enroll : allEnroll) {
                if(enroll.getEid()==byeid.getEid()){
                    flag=true;
                }
            }
        }

        if(flag==false){
            jsonResult.setCode(2);
            jsonResult.setInfo("没有报名该场考试");
            return jsonResult;

        }

        if(byeid!=null){//有该考场的试卷就返回

            AllTestList objectAllTest = writeReadJson.getObjectAllTest(byeid.getRadiojson(), byeid.getJudgejson(), byeid.getShortanswerjson());

            jsonResult.setInfo(objectAllTest);
            jsonResult.setCode(1);
            return  jsonResult;
        }
        return jsonResult;
    }

    //保存用户测试练习使用的测试题 随机生成  需要学科Id保存到TestType
    @RequestMapping("/SaveTestMapper.do")
    public String SaveTestMapper(TestType TestType) {

        Integer times = Integer.parseInt(TestType.getP_duration()) * 60;

        TestType.setP_duration(times.toString());

        //p_dbids    课程 p_qtypes 试题类型
        //判断同一个课程 不能选择同一种 类型的试题 如 A课程无法选择两种单选试题
        String p_dbids = TestType.getP_dbids();
        String[] split = p_dbids.split(",");

        String p_qtypes = TestType.getP_qtypes();
        String[] split1 = p_qtypes.split(",");

        LinkedHashSet<String> strings = new LinkedHashSet<>();
        for (int i = 0; i < split.length; i++) {
            String emp = split[i]+split1[i];
            strings.add(emp);
        }
        if(strings.size()!=split.length){

            return "redirect:http://127.0.0.1:8848/examsys/OptionTest.html?flag="+-1;
        }
        JsonResult studentTestMapper = testPaperService.getStudentTestMapper(TestType);

       //获取testTypeId
        String testTypeId = studentTestMapper.getInfo().toString();

        return "redirect:http://127.0.0.1:8848/examsys/TestMapper.html?testTypeId="+testTypeId;
        //return studentTestMapper;
    }

    //获取测试试题信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getTestMapper.do")
    public AllTestList getTestMapper(Integer testTypeId) throws IOException {

        TestType testType = testTypeDao.selectByPrimaryKey(testTypeId);

        //获取绑定的eTest的主键id
        String s = testType.geteTestId();


        AllTestList allTestList = testTypeService.getAllTestList(s);

        return allTestList;
    }

    //获取测试  试题选择信息 testType
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getTestTypeInfo.do")
    public TestType getTestTypeInfo(Integer testTypeId){

        return  testTypeService.selectByPrimaryKey(testTypeId);
    }
    //保存用户提交的测试试卷信息  解析字符串TestAnswer 以‘-’切割
    // testTypeId为用户选择类型主键
    //用户是否填写完成 没有完成返回并提醒用户
    @ResponseBody
    @CrossOrigin
    @RequestMapping("/saveTestAnswer.do")
   // public JsonResult saveTestAnswer(String TestAnswer,Integer testTypeId,Integer uid){
    public JsonResult saveTestAnswer(String TestAnswer,Integer testTypeId,Integer uId){

        JsonResult jsonResult = new JsonResult();

        //用户生成的试题 判断单选题个数
        List<ETest> alleTestByTId = testTypeService.findAlleTestByTId(testTypeId);


      /* TestType testType = testTypeService.selectByPrimaryKey(testTypeId);
        String p_qnums = testType.getP_qnums();
        String[] split1 = p_qnums.split(",");
        int count =0;
        for (int i = 0; i < split1.length; i++) {
            count+=Integer.parseInt(split1[i]);
        }

        String[] split = TestAnswer.split("-");

        if(split.length!=count){//试题没有完成 标记flag为-1 并返回该页面
            jsonResult.setInfo("请添加完试题");
            jsonResult.setCode(-1);
            return jsonResult;
        }*/


        //开始保存试题答案  提供TestAnswer字符串
        Answer answer = answerUtils.savaAnswer(TestAnswer, null, uId, null, alleTestByTId);

        jsonResult.setCode(1);
        jsonResult.setInfo(answer.getScore());

        return jsonResult;
    }


    //保存用户考试ExamMapper 答案操作

    /**
     *
     * @param TestAnswer 单选值 字符串格式 以‘-’间隔
     * @param shortAnswer  简答题 字符串格式 以‘-’间隔
     * @param uId 用户id
     * @param eId 考场id
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/saveExamAnswer.do")
    public JsonResult saveExamAnswer(String TestAnswer,String shortAnswer,Integer uId,Integer eId){


        ETest byeid = eTestDao.findByeid(eId);


        ArrayList<ETest> eTests = new ArrayList<>();

        eTests.add(byeid);

        answerUtils.savaAnswer(TestAnswer,shortAnswer,uId,byeid.getId(),eTests);

        JsonResult jsonResult = new JsonResult();

        jsonResult.setCode(1);
        jsonResult.setInfo("考试提交完毕");
        return jsonResult;

    }

    //练习题 用户需要查看答案

    @CrossOrigin
    @RequestMapping("/getTestAnswer.do")
    @ResponseBody
    public JsonResult getTestAnswer(String id){

        JsonResult jsonResult = new JsonResult();

        if(id.contains("R")){//需要查看单选题答案
            int rid = Integer.parseInt(id.substring(1));
            Radio radio = radioDao.selectByPrimaryKey(rid);

            jsonResult.setInfo(radio.getAnswer());
            jsonResult.setCode(1);

        }

        if(id.contains("J")){//需要查看单选题答案
            int rid = Integer.parseInt(id.substring(1));
            Judge judge = judgeDao.selectByPrimaryKey(rid);

            jsonResult.setInfo(judge.getAnswer());
            jsonResult.setCode(1);

        }
        return jsonResult;
    }

    @RequestMapping("/listAllETest.do")
    @ResponseBody
    public Map<String, Object> findAllRadios(String name,Integer page, Integer limit) {
        List<ETest> eTestList = eTestService.findAlls(name,page, limit);

        long total = ((Page) eTestList).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", eTestList); // 获取到的分页数据

        return map;
    }

    @RequestMapping("/queryAlleTests.do")
    @ResponseBody
    public JsonResult QueryById(Integer id) {
        ETest eTest = eTestService.QueryById(id);
        return new JsonResult(1, eTest); }


}
