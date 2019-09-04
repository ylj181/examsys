package com.qfedu.examsys.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.dao.ETestDao;
import com.qfedu.examsys.dao.TestTypeDao;
import com.qfedu.examsys.pojo.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lei
 * @Date 2019-9-3 19:34
 */


//json格式转换与转出
@Component
public class WriteReadJson {


    @Autowired(required = false)
    private ETestDao eTestDao;

    @Autowired(required = false)
    private TestTypeDao testTypeDao;

    //对象转换为json 传入一个对象
    public String WriteJson(Object obj) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        return  objectMapper.writeValueAsString(obj);
    }


    //json转换为对象
    //给定eTest主键id 需要字符串格式以，间隔 返回vo类
    public AllTestList ReadJson(String strJson) throws IOException {

        AllTestList allTestList = new AllTestList();

        List<Radio> radioList=new ArrayList<>();
        List<Judge> judgeList =new ArrayList<>();
        List<ShortAnswer> shortAnswerList=new ArrayList<>();

        String[] split = strJson.split(",");

        ObjectMapper objectMapper =new ObjectMapper();


        for (int i = 0; i <split.length ; i++) {

            Integer eid = Integer.parseInt(split[i]);
            ETest eTest = eTestDao.selectByPrimaryKey(eid);
            String judgejson = eTest.getJudgejson();
            List<Judge> judges = objectMapper.readValue(judgejson,new TypeReference<List<Judge>>() { });

            String shortanswerjson = eTest.getShortanswerjson();
            List<ShortAnswer> shortAnswers = objectMapper.readValue(shortanswerjson,new TypeReference<List<ShortAnswer>>() { });

            String radiojson = eTest.getRadiojson();
            List<Radio> radios = objectMapper.readValue(radiojson,new TypeReference<List<Radio>>() { });

            if(!judgejson.equals("null") && !judgejson.equals("[]")){

                judgeList.addAll(judges);
            }
            if(!radiojson.equals("null") && !radiojson.equals("[]")){

                radioList.addAll(radios);
            }

            if(!shortanswerjson.equals("null") && !shortanswerjson.equals("[]")){

                shortAnswerList.addAll(shortAnswers);
            }

        }

        allTestList.setAnswerList(shortAnswerList);
        allTestList.setRadioList(radioList);
        allTestList.setJudgeList(judgeList);
        return allTestList ;
    }
    //json格式转换为对象 需要json字符串 返回Vo  allTestList
    public AllTestList getObjectAllTest(String StrRadio,String StrJudge,String StrShort) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Radio> radioList=new ArrayList<>();
        List<Judge> judgeList =new ArrayList<>();
        List<ShortAnswer> shortAnswerList=new ArrayList<>();
        AllTestList allTestList = new AllTestList();

       if(StrJudge!=null && !StrJudge.equals("")){
           judgeList = objectMapper.readValue(StrJudge,new TypeReference<List<Judge>>() { });
           allTestList.setJudgeList(judgeList);
       }
        if(StrRadio!=null && !StrRadio.equals("")){
            radioList= objectMapper.readValue(StrRadio,new TypeReference<List<Radio>>() { });
            allTestList.setRadioList(radioList);
        }
        if(StrShort!=null && !StrShort.equals("")){

            shortAnswerList= objectMapper.readValue(StrShort,new TypeReference<List<ShortAnswer>>() { });

            allTestList.setAnswerList(shortAnswerList);
        }

        return  allTestList;

    }
}
