package com.qfedu.examsys.aftercontroller;/*
 *
 *   @Author ylj
 *   @Date   2019/9/5 18:15
 */

import com.qfedu.examsys.service.ETestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ETestController {

    @Autowired
    private ETestService eTestService;

    @RequestMapping("/ETest/findETestById.do")
    @ResponseBody
    public Map<String,Object> findETestById(Integer id){

        Map<String , Object> map = new HashMap<>();

        Integer status = eTestService.findETestById(id);

        map.put("info",status);

        return map;
    }
}
