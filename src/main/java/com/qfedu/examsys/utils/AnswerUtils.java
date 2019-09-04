package com.qfedu.examsys.utils;

import com.qfedu.examsys.dao.AnswerDao;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.testTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lei
 * @Date 2019-9-3 17:30
 */

//保存用户提交答案
@Component
public class AnswerUtils {

    @Autowired(required = false)
    private AnswerDao answerDao;

    @Autowired
    private testTypeService testTypeService;

    @Autowired
    private WriteReadJson writeReadJson;

    public void savaAnswer(String answerInfo, Integer uid , Integer eTid, List<ETest> eTestList){
        String[] split = answerInfo.split("-");

        String  judges ="";
        String shorts ="";
        String radios ="";

        for (int i = 0; i <split.length ; i++) {
            if(split[i].contains("R")){//单选
                radios+=split[i]+"-";
            }
            if(split[i].contains("J")){ //判断
                judges+=split[i]+"-";
            }
            if(split[i].contains("S")){ //简答
                shorts+=split[i]+"-";
            }
        }
        String[] judgesSplit = judges.split("-");

        String[] shortSplit = shorts.split("-");

        String[] radioSplit = radios.split("-");

        Answer answer = new Answer();
        answer.setEtid(eTid);
        answer.setJudges(judges);
        answer.setRadios(radios);
        answer.setShorts(shorts);
        answer.setUid(uid);

        //总分
        Integer count =0;

        //总分判断

        AllTestList allTestList = new AllTestList();
        String judgejson=null;
        String  shortJson=null;
        String radiojson=null;

        List<Radio> radioList=new ArrayList<>();
        List<Judge> judgeList =new ArrayList<>();
        List<ShortAnswer> shortAnswerList=new ArrayList<>();

        for (int i = 0; i <eTestList.size(); i++) {

            judgejson = eTestList.get(i).getJudgejson();
            shortJson = eTestList.get(i).getShortanswerjson();
            radiojson = eTestList.get(i).getRadiojson();

            try {
                if(!judgejson.equals("null") && !judgejson.equals("[]")){

                    AllTestList objectAllTest = writeReadJson.getObjectAllTest(null, judgejson, null);

                    judgeList.addAll(objectAllTest.getJudgeList());

                }
                if(!shortJson.equals("null")  && !shortJson.equals("[]")){


                    AllTestList objectAllTest = writeReadJson.getObjectAllTest(null, null, shortJson);

                    shortAnswerList.addAll(objectAllTest.getAnswerList());

                }
                if(!radiojson.equals("null") && !radiojson.equals("[]")){

                    AllTestList objectAllTest = writeReadJson.getObjectAllTest(radiojson, null, null);

                    radioList.addAll(objectAllTest.getRadioList());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //开始判断
        if(radioList.size()!=0){
            for (int i = 0; i < radioList.size(); i++) {
                String emp =radioSplit[i];
                String R =emp.substring(emp.length()-1);
                if( radioList.get(i).getAnswer().equals(R)){
                    count+=5;
                }
            }
        }
        if(judgeList.size()!=0){
            for (int i = 0; i < judgeList.size(); i++) {
                String emp = judgesSplit[i];
                String J =emp.substring(emp.length()-2);
                if( judgeList.get(i).getAnswer().equals(J)){
                    count+=5;
                }
            }

        }

        //简答题 另需判断
        if(shortAnswerList.size()!=0){

        }
        answer.setScore(count);

        answerDao.insertSelective(answer);
    }
}
