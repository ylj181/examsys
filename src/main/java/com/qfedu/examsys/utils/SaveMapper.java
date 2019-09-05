package com.qfedu.examsys.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.dao.ETestDao;
import com.qfedu.examsys.pojo.AllTestList;
import com.qfedu.examsys.pojo.ETest;
import com.qfedu.examsys.pojo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Lei
 * @Date 2019-9-1 15:44
 */


//保存试题到 eTest  可以保存测试题  考试题
@Component
public class SaveMapper {

    @Autowired(required =  false)
    private ETestDao eTestDao;

    /**
     *
     * @param allTestList 试题集合
     * @param subjectId  学科id
     * @param eid  考场id
     * @Param eTestName 试卷名称
     */
    public JsonResult saveMapper(AllTestList allTestList,Integer subjectId,Integer eid,String eTestName){
        ObjectMapper objectMapper = new ObjectMapper();

        ETest eTest = new ETest();

        String radioStr = null;
        String answerStr =null;
        String  judgeStr= null;
        try {
            radioStr = objectMapper.writeValueAsString(allTestList.getRadioList());

            answerStr  = objectMapper.writeValueAsString(allTestList.getAnswerList());

            judgeStr = objectMapper.writeValueAsString(allTestList.getJudgeList());


            //关联的exam
            eTest.setEid(eid);

            //考卷名称
            eTest.setName(eTestName);

            //考卷所属科目
            eTest.setSubjectid(subjectId);

            eTest.setJudgejson(judgeStr);

            eTest.setRadiojson(radioStr);

            eTest.setShortanswerjson(answerStr);



        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        eTestDao.insertSelective(eTest);

        JsonResult jsonResult = new JsonResult();

        jsonResult.setInfo(eTest);

        jsonResult.setCode(1);

        return jsonResult;
    }

}
