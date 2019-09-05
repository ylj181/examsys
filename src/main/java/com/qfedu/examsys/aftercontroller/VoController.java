package com.qfedu.examsys.aftercontroller;/*
 *
 *   @Author ylj
 *   @Date   2019/9/5 20:40
 */

import com.github.pagehelper.Page;
import com.qfedu.examsys.pojo.Vo;
import com.qfedu.examsys.service.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VoController {

    @Autowired
    private VoService voService;

    @CrossOrigin
    @RequestMapping("/vo/findBySidAndEtId.do")
    public Map<String,Object> findBySidAndEtId(Integer sid, Integer etid,Integer page,Integer limit){

        Map<String, Object> map = new HashMap<>();

        List<Vo> voList = voService.findBySidAndEtId(sid, etid, page, limit);

        long total = ((Page) voList).getTotal();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", total);
        map.put("data", voList);

        return map;
    }
}
