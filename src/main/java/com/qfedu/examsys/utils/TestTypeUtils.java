package com.qfedu.examsys.utils;

import com.qfedu.examsys.dao.JudgeDao;
import com.qfedu.examsys.dao.RadioDao;
import com.qfedu.examsys.dao.ShortAnswerDao;
import com.qfedu.examsys.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Lei
 * @Date 2019-9-1 14:53
 */
@Component
public class TestTypeUtils {

    @Autowired(required = false)
    private ShortAnswerDao shortAnswerDao;

    @Autowired(required = false)
    private RadioDao radioDao;

    @Autowired(required = false)
    private JudgeDao judgeDao;

    @Autowired
    private TestMapperUtils mapperUtils;

    @Autowired(required = false)
    private SaveMapper saveMapper;

    //public  List<AllTestList> getTestmapperList(TestType testType){
    public  HashMap<Object, Object> getTestmapperList(TestType testType){

             /*   private String p_name; //试卷名称
        private String p_duration;  // 考试时长
        private String p_section_names[];//章节名称
        // private  String  p_section_remarks[];  // 1 单选或者 2多选或是  3简单
        private String p_dbids[]; // 题库名称
        private String p_qtypes[];//题型 判断/选择/简答
        private Integer p_levels[]; //难易级别
        private Integer p_qnums[];//试题数量*/

        HashMap<Object, Object> map = new HashMap<>();

        //增加试题的主键id
        String eTestid ="";

        String[] types = testType.getP_qtypes().split(",");//单选多选类型

        String[] names = testType.getP_dbids().split(","); //题库名称 == 学科名称

        String[] nums = testType.getP_qnums().split(",");  //选择的个数

        //随机获取各科题库
        List<Radio> radioList =null; // 准备radio 随机题
        List<Judge> judgeList = null; // 准备Judge 随机题
        List<ShortAnswer> shortAnswerList = null; // 准备ShortAnswes 随机题



        AllTestList allTestList = null;

        ArrayList<AllTestList> allTestLists = new ArrayList<>();

        for (int i = 0; i <types.length ; i++) {

            allTestList=new AllTestList();

            Integer nameSubjectId = Integer.valueOf(names[i]);

            Integer count = Integer.valueOf(nums[i]);

            if(types[i].equals("1")){ //选择了单选题
                //使用subjectId 代表题库名称
                List randomList = mapperUtils.getRandomList(radioDao.findRadios(nameSubjectId), count);

                allTestList.setRadioList(randomList);

            }else if(types[i].equals("2")){ //表示选择判断
                judgeList= mapperUtils.getRandomList(judgeDao.findJudges(nameSubjectId),count);
                allTestList.setJudgeList(judgeList);

            }else {//选择了简答
                shortAnswerList= mapperUtils.getRandomList(shortAnswerDao.findShortAnswers(nameSubjectId),count);
                allTestList.setAnswerList(shortAnswerList);
            }

            allTestLists.add(allTestList);


            JsonResult jsonResult = saveMapper.saveMapper(allTestList, nameSubjectId, null, testType.getP_name());

            ETest info =(ETest) jsonResult.getInfo();

            eTestid+=info.getId()+",";

        }

        map.put("eTestId",eTestid);
        map.put("allTesttLists",allTestLists);

        //return allTestLists;

        return map;


    }


}
