package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.AllTestList;
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

    //试卷信息生成 并保存到Test  需要管理员开启exam 考场 Exam exam
    @RequestMapping("/getExamMapper.do")
    @ResponseBody
    public JsonResult getPaperTest(Exam exam,String eTestName){

        testPaperService.saveStudentExamMapper(exam,eTestName);
        return  null;
    }

    //用户测试练习使用的测试题 随机生成  需要学科Id

    public JsonResult getTestMapper(Integer subjectId){

        AllTestList testMapper = testPaperService.getTestMapper(subjectId);

        JsonResult jsonResult = new JsonResult();

        jsonResult.setCode(1);

        jsonResult.setInfo(testMapper);

        return jsonResult;
    }




}
