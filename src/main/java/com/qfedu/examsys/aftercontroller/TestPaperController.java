package com.qfedu.examsys.aftercontroller;
import com.qfedu.examsys.dao.TestTypeDao;
import com.qfedu.examsys.pojo.AllTestList;
import com.qfedu.examsys.pojo.Exam;
import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.TestType;
import com.qfedu.examsys.service.TestPaperService;
import com.qfedu.examsys.service.testTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.LinkedHashSet;

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

        return "redirect:http://127.0.0.1:8020/examsys/test.html?testTypeId="+testTypeId;
        //return studentTestMapper;
    }

    @ResponseBody
    @RequestMapping("/getTestMapper.do")
    public AllTestList getTestMapper(Integer testTypeId) throws IOException {

        TestType testType = testTypeDao.selectByPrimaryKey(testTypeId);

        //获取绑定的eTest的主键id
        String s = testType.geteTestId();

        AllTestList allTestList = testTypeService.getAllTestList(s);

        return allTestList;

    }



}
