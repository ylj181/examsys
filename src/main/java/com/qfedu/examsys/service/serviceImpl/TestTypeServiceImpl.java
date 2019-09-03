package com.qfedu.examsys.service.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.dao.ETestDao;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.testTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lei
 * @Date 2019-8-31 16:33
 */

@Service
public class TestTypeServiceImpl implements testTypeService {

    @Autowired(required = false)
    private ETestDao eTestDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(TestType record) {
        return 0;
    }

    @Override
    public int insertSelective(TestType record) {
        return 0;
    }

    @Override
    public TestType selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TestType record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TestType record) {
        return 0;
    }


    @Override
    public AllTestList getAllTestList(String s) throws IOException {
        AllTestList allTestList = new AllTestList();

        List<Radio> radioList=new ArrayList<>();
        List<Judge> judgeList =new ArrayList<>();
        List<ShortAnswer> shortAnswerList=new ArrayList<>();

        String[] split = s.split(",");

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
        return allTestList;
    }

}
