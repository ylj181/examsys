package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.Answer;
import com.qfedu.examsys.pojo.User;
import com.qfedu.examsys.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    /**
     * 根据考试表Id和用户Id查询本次考试的成绩
     * @param eid 考试表Id
     * @param uid 用户Id
     * @return  map
     */
    @CrossOrigin
    @RequestMapping("/answer/selectAnswerByExamIdAndUserId.do")
    @ResponseBody
    public Map<String,Object> selectAnswerByExamIdAndUserId(Integer eid,Integer uid){

        Map<String, Object> map = new HashMap<>();

        Answer answer = answerService.selectAnswerByExamIdAndUserId(eid, uid);

        if (answer != null) {
            map.put("code",1);
            map.put("info",answer.getScore());
        }

        return map;
    }
}
