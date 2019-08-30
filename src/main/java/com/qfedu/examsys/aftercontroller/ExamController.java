package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.Exam;
import com.qfedu.examsys.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * 展示所有的考试信息列表
     * @return  map
     */
    @RequestMapping("/exam/findAllExams.do")
    @ResponseBody
    public Map<String,Object> findAllExams(){

        Map<String, Object> map = new HashMap<>();

        List<Exam> examList = examService.findAllExams();

        if (examList.size() != 0){
            map.put("code",1);
            map.put("info",examList);
        }

        return map;
    }
}
