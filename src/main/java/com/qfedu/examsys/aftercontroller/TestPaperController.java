package com.qfedu.examsys.aftercontroller;
import com.qfedu.examsys.pojo.Exam;
import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.TestType;
import com.qfedu.examsys.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    //用户测试练习使用的测试题 随机生成  需要学科Id保存到TestType
    @RequestMapping("/getTestMapper.do")
    public String getTestMapper(TestType TestType, Model model) throws IOException, ServletException {

        JsonResult studentTestMapper = testPaperService.getStudentTestMapper(TestType);

        model.addAttribute("studentTestMapper",studentTestMapper);

       //return "redirect:http://127.0.0.1:8020/examsys/TestMapper.html?sid=1";

       return "redirect:http://127.0.0.1:8020/examsys/test.html?sid=1";
      // return "forward:/TestMapper.html";

    }

    //用户提交试卷操作 需要answer对象 和 是否是测试或者考试
    // 练习题为 1  考试为 0


}
