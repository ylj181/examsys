package com.qfedu.examsys.beforecontroller;

import com.github.pagehelper.Page;
import com.qfedu.examsys.pojo.JsonResult;
import com.qfedu.examsys.pojo.Judge;
import com.qfedu.examsys.pojo.Radio;
import com.qfedu.examsys.pojo.Subject;
import com.qfedu.examsys.service.JudgeService;
import com.qfedu.examsys.service.RadioService;
import com.qfedu.examsys.service.ShortAnswerService;
import com.qfedu.examsys.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leox
 * 展示题库
 */
@Controller
@RequestMapping("/radios")
public class ShowAllRJS {

    @Autowired
    private RadioService radioService;
    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ShortAnswerService shortAnswerService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 题库所有选择题展示
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/listAllRadios.do")
    @ResponseBody
    public Map<String, Object> findAllRadios(String name,Integer page, Integer limit) {
        List<Radio> radioList = radioService.findAllRadios(name,page, limit);

        long total = ((Page) radioList).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", radioList); // 获取到的分页数据

        return map;
    }

    /**
     * 绑定下拉菜单展示学科名字
     * @return
     */
    @RequestMapping("/findSubjectName.do")
    @ResponseBody
    public JsonResult findAllSubject() {
        List<Subject> allSubject = subjectService.findAllSubject();
        return new JsonResult(1, allSubject);
    }

    /**
     * 展示所有判断题
     * @param name
     * @param page
     * @param limit
     * @return
     */

    @RequestMapping("/listAllJudges.do")
    @ResponseBody
    public Map<String, Object> findAllJudges(String name, Integer page, Integer limit) {
        List<Judge> allJudges = judgeService.findAllJudges(name, page, limit);
        long total = ((Page) allJudges).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", allJudges); // 获取到的分页数据

        return map;
    }


    @RequestMapping("/listAllShortAnswer.do")
    @ResponseBody
    public Map<String, Object> listAllShortAnswer(String name, Integer page, Integer limit) {
        List<Judge> allShortAnswers = shortAnswerService.findAllShortAnswers(name, page, limit);
        long total = ((Page) allShortAnswers).getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0); // 结合layui的表格组件，0表示成功
        map.put("msg", "");
        map.put("count", total);// 表中总记录数
        map.put("data", allShortAnswers); // 获取到的分页数据

        return map;
    }
}
