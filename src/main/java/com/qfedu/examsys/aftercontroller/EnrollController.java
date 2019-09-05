package com.qfedu.examsys.aftercontroller;


import com.github.pagehelper.Page;
import com.qfedu.examsys.pojo.Enroll;
import com.qfedu.examsys.service.EnrollService;
import com.qfedu.examsys.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private ExamService examService;

    /**
     * 向报名表中添加报名信息
     *
     * @param uid 用户Id
     * @param eid 报名表Id
     * @return map
     */
    @CrossOrigin
    @RequestMapping("/enroll/addEnrollInfo.do")
    @ResponseBody
    public Map<String, Object> addEnrollInfo(Integer uid, Integer eid, Integer subjectid) {
        Map<String, Object> map = new HashMap<>();

        int i = enrollService.addEnrollInfo(uid, eid, subjectid);

        if (i > 0) {
            map.put("code", 1);
        }

        return map;

    }


//    /**
//     * 展示本次报名的考试信息
//     * @param id  报名的考试Id
//     * @return  map
//     */
//    @RequestMapping("/enroll/selectEnrollById.do")
//    @ResponseBody
//    public Map<String,Object> selectEnrollById(Integer id){
//        Map<String, Object> map = new HashMap<>();
//
//        Enroll enroll = enrollService.selectEnrollById(id);
//
//        if (enroll != null){
//            map.put("code",1);
//            map.put("info",enroll);
//            System.out.println(enroll.getSubjectId());
//        }
//
//        return map;
//    }

    /**
     * 展示该学生所有的考试信息
     *
     * @return map
     */
    @CrossOrigin
    @RequestMapping("/enroll/findAllEnroll.do")
    @ResponseBody
    public Map<String, Object> findAllEnroll(Integer uid, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();

        List<Enroll> enrollList = enrollService.findAllEnroll(uid, page, limit);

        long total = ((Page) enrollList).getTotal();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", total);
        map.put("data", enrollList);

        return map;
    }

    /**
     * 查询某学科的所有报名信息
     *
     * @param sid   学科id
     * @param page
     * @param limit
     * @return map
     */
    @CrossOrigin
    @RequestMapping("/enroll/findAllEnrolls.do")
    @ResponseBody
    public Map<String, Object> findAllEnrolls(Integer sid, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();

        List<Enroll> allEnrolls = enrollService.findAllEnrolls(sid, page, limit);

        long total = ((Page) allEnrolls).getTotal();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", total);
        map.put("data", allEnrolls);

        return map;
    }

    @CrossOrigin
    @RequestMapping("/enroll/findEnrollsByUidAndSubjectIdAndCreatetime.do")
    @ResponseBody
    public Map<String, Object> findEnrollsByUidAndSubjectIdAndCreatetime(Integer uid, Integer subjectId, Date createtime, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();

        List<Enroll> enrolls = enrollService.findEnrollsByUidAndSubjectIdAndCreatetime(uid, subjectId, createtime, page, limit);

        long total = ((Page) enrolls).getTotal();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", total);
        map.put("data", enrolls);

        return map;

    }
}
