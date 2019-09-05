package com.qfedu.examsys.beforecontroller;


import com.github.pagehelper.Page;
import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.Radio;
import com.qfedu.examsys.pojo.ShortAnswer;
import com.qfedu.examsys.service.JudgeService;
import com.qfedu.examsys.service.RadioService;
import com.qfedu.examsys.service.ShortAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 录入试题
 */
@Controller
@RequestMapping("/select")
public class selectController {

    @Autowired
    private RadioService radioService;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ShortAnswerService shortAnswerService;


    @RequestMapping("/ListAnythingRadios.do")
    @ResponseBody
    public Map<String, Object> findAllRadios(String name,Integer page, Integer limit) {
        List<Radio> radioList = radioService.findAnythingRadios(name,page, limit);

        long total = ((Page) radioList).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", radioList); // 获取到的分页数据

        return map;
    }

    /**
     * 选择题录入
     */
    @RequestMapping("/updateRadiosRecentType.do")
    @ResponseBody
    public JsonResult updateRadiosRecentType(Radio radio) {
        radioService.updateRecentType(radio);
        return new JsonResult(1, null);
    }

    /**
     * 选择题批量录入
     * @param ids
     * @return
     */
    @RequestMapping("/updateRadiosRecentTypeByIds.do")
    @ResponseBody
    public JsonResult updateRadiosByIds(@RequestParam(value = "ids") List<Integer> ids) {
        radioService.updateRecentTypeByIds(ids);
        return new JsonResult(1, null);

    }


    @RequestMapping("/ListAnythingJudges.do")
    @ResponseBody
    public Map<String, Object> findAllJudges(String name,Integer page, Integer limit) {
        List<Judge> judgeList = judgeService.findAnythingJudge(name,page, limit);

        long total = ((Page) judgeList).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", judgeList); // 获取到的分页数据

        return map;
    }

    /**
     * 判断题录入
     */
    @RequestMapping("/updateJudgesRecentType.do")
    @ResponseBody
    public JsonResult updateJudgesRecentType(Judge judge) {
        judgeService.updateRecentType(judge);
        return new JsonResult(1, null);
    }

    /**
     * 判断题批量录入
     * @param ids
     * @return
     */
    @RequestMapping("/updateJudgesRecentTypeByIds.do")
    @ResponseBody
    public JsonResult updateJudgesByIds(@RequestParam(value = "ids") List<Integer> ids) {
        judgeService.updateRecentTypeByIds(ids);
        return new JsonResult(1, null);

    }


    @RequestMapping("/ListAnythingShortAnswer.do")
    @ResponseBody
    public Map<String, Object> findAllShortAnswer(String name,Integer page, Integer limit) {
        List<ShortAnswer> shortAnswerList = shortAnswerService.findAnythingShortAnswer(name,page, limit);

        long total = ((Page) shortAnswerList).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", shortAnswerList); // 获取到的分页数据

        return map;
    }

    /**
     * 简答题录入
     */
    @RequestMapping("/updateShortAnswerRecentType.do")
    @ResponseBody
    public JsonResult updateShortAnswerRecentType(ShortAnswer shortAnswer) {
        shortAnswerService.updateRecentType(shortAnswer);
        return new JsonResult(1, null);
    }

    /**
     * 简答题批量录入
     * @param ids
     * @return
     */
    @RequestMapping("/updateShortAnswerRecentTypeByIds.do")
    @ResponseBody
    public JsonResult updateShortAnswerByIds(@RequestParam(value = "ids") List<Integer> ids) {
        shortAnswerService.updateRecentTypeByIds(ids);
        return new JsonResult(1, null);

    }
}
