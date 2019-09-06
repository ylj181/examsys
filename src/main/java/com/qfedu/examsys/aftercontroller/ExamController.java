package com.qfedu.examsys.aftercontroller;

import com.github.pagehelper.Page;
import com.qfedu.examsys.pojo.Exam;
import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private TestPaperController testPaperController;

    /**
     * 展示所有的考试信息列表
     * @return  map
     */
    @CrossOrigin
    @RequestMapping("/exam/findAllExams.do")
    @ResponseBody
    public Map<String,Object> findAllExams(Integer page,Integer limit){

        Map<String, Object> map = new HashMap<>();

        List<Exam> examList = examService.findAllExams(page, limit);

        long total = ((Page) examList).getTotal();


        map.put("code",0);
        map.put("msg","");
        map.put("count",total);
        map.put("data",examList);


        return map;
    }


    @RequestMapping("/exam/findExamById.do")
    @ResponseBody
    public Map<String,Object> findExamById(Integer id){
        Map<String, Object> map = new HashMap<>();

        Exam exam = examService.findExamById(id);

        if (exam != null) {
            map.put("code",1);
            map.put("info",exam);
        }

        return map;
    }

    @CrossOrigin
    @RequestMapping("/exam/findExamsBySubjectId.do")
    @ResponseBody
    public Map<String,Object> findExamsBySubjectId(Integer subjectId, Integer page,Integer limit){
        Map<String, Object> map = new HashMap<>();

        List<Exam> exams = examService.findExamsBySubjectId(subjectId, page, limit);

        long total = ((Page) exams).getTotal();

        map.put("code",0);
        map.put("msg","");
        map.put("count",total);
        map.put("data",exams);

        return map;
    }

    @CrossOrigin
    @RequestMapping("/addExamInfo.do")
    public JsonResult addExamInfo(Exam exam,String eName) throws IOException {
        examService.addExamInfo(exam);
        if (exam !=null) {

            testPaperController.getPaperTest(exam.getId(), exam.getSubjectid(), eName, 3);
            return new JsonResult(1,null);

        }
        return new JsonResult( 0,"添加失败" );

    }
}
