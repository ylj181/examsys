package com.qfedu.examsys.aftercontroller;
import com.qfedu.examsys.dao.TestTypeDao;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.TestPaperService;
import com.qfedu.examsys.service.testTypeService;
import com.qfedu.examsys.utils.AnswerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

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



    //试卷信息生成 并保存到eTest  需要管理员开启exam 考场 Exam exam  设置考卷名称
    //正常code 为1 返回etest对象
    @RequestMapping("/getExamMapper.do")
    @ResponseBody
    public JsonResult getPaperTest(Exam exam,String eTestName){

        return  testPaperService.getStudentExamMapper(exam, eTestName);
    }

    //保存用户测试练习使用的测试题 随机生成  需要学科Id保存到TestType
    @RequestMapping("/SaveTestMapper.do")
    public String SaveTestMapper(TestType TestType ) {

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

            return "redirect:http://127.0.0.1:8020/examsys/OptionTest.html?flag="+-1;
        }
        JsonResult studentTestMapper = testPaperService.getStudentTestMapper(TestType);

       //获取testTypeId
        String testTypeId = studentTestMapper.getInfo().toString();

        return "redirect:http://127.0.0.1:8020/examsys/TestMapper.html?testTypeId="+testTypeId;
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

    //获取测试试题选择信息 testType
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getTestTypeInfo.do")
    public TestType getTestTypeInfo(Integer id){

        return  testTypeService.selectByPrimaryKey(id);
    }
    //保存用户提交的测试试卷信息  解析字符串TestAnswer 以‘-’切割
    // testTypeId为用户选择类型主键
    //用户是否填写完成 没有完成返回并提醒用户
    @ResponseBody
    @CrossOrigin
    @RequestMapping("/saveTestAnswer.do")
    public JsonResult saveTestAnswer(String TestAnswer,Integer testTypeId){

        JsonResult jsonResult = new JsonResult();

        //用户生成的试题
        List<ETest> alleTestByTId = testTypeService.findAlleTestByTId(testTypeId);

        TestType testType = testTypeService.selectByPrimaryKey(testTypeId);
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
        }

        jsonResult.setCode(1);
        jsonResult.setInfo("添加完成");

        //开始保存试题答案  提供TestAnswer字符串
        answerUtils.savaAnswer(TestAnswer,1,1,alleTestByTId);

        return jsonResult;
    }
}
