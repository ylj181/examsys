package com.qfedu.examsys.aftercontroller;


import com.qfedu.examsys.pojo.Enroll;
import com.qfedu.examsys.pojo.User;
import com.qfedu.examsys.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    /**
     * 向报名表中添加报名信息
     * @param session  HttpSession对象
     * @return  返回 map
     */
    @RequestMapping("/enroll/addEnrollInfo.do")
    @ResponseBody
    public Map<String,Object> addEnrollInfo(HttpSession session,Integer eid){
        Map<String, Object> map = new HashMap<>();

        //从session中获取登录用户对象
        User user = (User) session.getAttribute("user");
        //获取登录用户的Id
        Integer uid = user.getId();

        int i = enrollService.addEnrollInfo(uid, eid);

        if (i > 0) {
            map.put("code",1);
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
     * @return map
     */
    @RequestMapping("/enroll/findAllEnroll.do")
    @ResponseBody
    public Map<String,Object> findAllEnroll(Integer uid){
        Map<String, Object> map = new HashMap<>();


        List<Enroll> enrollList = enrollService.findAllEnroll(uid);

        if (enrollList.size() != 0){
            map.put("code",1);
            map.put("info",enrollList);
        }

        return map;
    }
}
