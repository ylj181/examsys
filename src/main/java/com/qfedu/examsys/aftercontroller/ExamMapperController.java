package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.dao.AnswerDao;
import com.qfedu.examsys.dao.ETestDao;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.utils.WriteReadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lei
 * @Date 2019-9-5 22:13
 */

@Controller
public class ExamMapperController {

    @Autowired(required = false)
    private AnswerDao answerDao;

    @Autowired(required = false)
    private ETestDao  eTestDao;

    @Autowired(required = false)
    private WriteReadJson writeReadJson;




    //保存考试试卷中 用户每次单选提交的操作日志

    //保存到 answer表中

    //http://localhost:8080/examsys/savaExamOneAnswer.do?uId=3&eId=5&oneAnswer=R20-C
    @ResponseBody
    @RequestMapping("/savaExamOneAnswer.do")
    @CrossOrigin
    public String savaExamOneAnswer(Integer uId,Integer eId,String oneAnswer) throws IOException {

        ETest byeid = eTestDao.findByeid(eId);
        AllTestList objectAllTest = writeReadJson.getObjectAllTest(byeid.getRadiojson(), byeid.getJudgejson(), byeid.getShortanswerjson());
        // 获取radio试题的个数
        List<Radio> radioList = objectAllTest.getRadioList();
        int radioSize =radioList.size();
        StringBuffer sb = new StringBuffer();
        Answer answer = answerDao.findOneByuidAndeTid(uId, byeid.getId());
        Answer answer1 = new Answer();

        if(oneAnswer.contains("R")){//单选题提交事件

            if(answer==null){

                //R14-null&R12-null&

                for (int i = 0; i < radioSize; i++) {
                    sb.append("R"+radioList.get(i).getId()+"-null&");
                }
                //保存自定义的空值
                answer1.setRadios(sb.toString());
                answer1.setUid(uId);
                answer1.setEtid(byeid.getId());
                answerDao.insertSelective(answer1);
            }

            //确保split是存有数据
            answer = answerDao.findOneByuidAndeTid(uId, byeid.getId());

            String[] split =answer.getRadios().split("&");

            for (int i = 0; i <radioSize ; i++) {
                String emp = oneAnswer.split("-")[0].substring(1);
                if(radioList.get(i).getId().toString().equals(emp)){
                    split[i]=oneAnswer;
                    break;
                }
            }
            sb.delete(0,sb.length());

            for (int i = 0; i < split.length; i++) {
                sb.append(split[i]+"&");
            }
            String s = sb.toString();
            answerDao.updateByuidAndeTid(uId,byeid.getId(),s);

        }
        return  null;
    }

    //获取用户提交的单选按钮操作

    @ResponseBody
    @CrossOrigin
    @RequestMapping("/getRadioAnswer.do")
    public JsonResult getRadioAnswer(Integer uId,Integer eId){
        JsonResult jsonResult = new JsonResult();
        ETest byeid = eTestDao.findByeid(eId);

        Answer oneByuidAndeTid = answerDao.findOneByuidAndeTid(uId, byeid.getId());

        if(oneByuidAndeTid==null){
            jsonResult.setCode(0);
            jsonResult.setInfo(null);
            return jsonResult;
        }

        String radios = oneByuidAndeTid.getRadios();

        String[] split = radios.split("&");

        //存放数据
        ArrayList<Object> list = new ArrayList<>();

        for (int i = 0; i <split.length ; i++) {
            if(!split[i].contains("null")){
                list.add(split[i]);
            }
        }
        jsonResult.setInfo(list);
        jsonResult.setCode(1);
        return jsonResult;

    }

}
