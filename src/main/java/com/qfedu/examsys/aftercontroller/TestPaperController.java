package com.qfedu.examsys.aftercontroller;
import com.qfedu.examsys.pojo.Answer;
import com.qfedu.examsys.pojo.Exam;
import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //试卷信息生成 并保存到eTest  需要管理员开启exam 考场 Exam exam  设置考卷名称
    //正常code 为1 返回etest对象
    @RequestMapping("/getExamMapper.do")
    @ResponseBody
    public JsonResult getPaperTest(Exam exam,String eTestName){

        return  testPaperService.getStudentExamMapper(exam, eTestName);
    }

    //用户测试练习使用的测试题 随机生成  需要学科Id
    @ResponseBody
    @RequestMapping("/getTestMapper.do")
    public JsonResult getTestMapper(Integer subjectId){

        return  testPaperService.getStudentTestMapper();

    }

    //用户提交试卷操作 需要answer对象 和 是否是测试或者考试
    // 练习题为 1  考试为 0
    public  String saveTestMapper(Answer answer,Integer flag){

        testPaperService.saveAnswer(answer,1);



        return  null;

    }

}
