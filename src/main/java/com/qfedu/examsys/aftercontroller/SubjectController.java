package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.Subject;
import com.qfedu.examsys.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @CrossOrigin
    @RequestMapping("/subject/findAllSubject.do")
    @ResponseBody
    public Map<String,Object> findAllSubject(){

        Map<String, Object> map = new HashMap<>();

        List<Subject> subjectList = subjectService.findAllSubject();

        if (subjectList.size() != 0){
            map.put("code",1);
            map.put("info",subjectList);
        }

        return map;
    }

    @CrossOrigin
    @RequestMapping("/findSubject.do")
    @ResponseBody
    public JsonResult courseAllList(){
        List<Subject> list = subjectService.findSub();
        return new JsonResult( 1,list );
    }
}
