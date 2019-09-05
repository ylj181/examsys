package com.qfedu.examsys.aftercontroller;

import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.AnswerService;
import com.qfedu.examsys.service.ETestService;
import com.qfedu.examsys.utils.WriteReadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 *          试卷评分
 *
 * @Author imlee
 * @Date 2019-09-04 17:20
 */

@CrossOrigin
@RestController
public class GradePapers {

    @Autowired
    private WriteReadJson writeReadJson;

    @Autowired
    private ETestService eTestService;

    @Autowired
    private AnswerService answerService;

    /**
     *          通过考试ID 查询 试卷内容
     *
     * @Author  imlee
     * @param eid       考试ID
     * @return          试卷内容(简答题)
     */
    @RequestMapping("/getETest.do")
    public JsonResult getETest(String eid) {

        //  通过 考试id 查询 试卷id
        Integer eTid = eTestService.findETestIdByEid(Integer.parseInt(eid));

        try {
            //  通过试卷id 查找试卷内容
            AllTestList allTestList = writeReadJson.ReadJson("" + eTid);
            //              查询  简答题的内容
            List<ShortAnswer> answerList = allTestList.getAnswerList();
            //              返回简答题到前台
            return new JsonResult(1, answerList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JsonResult(0, "查询失败！");
    }

    /**
     *          通过试卷ID 查询所有还未批改的答题信息
     *
     * @Author  imlee
     * @param eid       考试ID
     * @return          还未批改的答题信息
     */
    @RequestMapping("/getAnswerOfUsers.do")
    public JsonResult getAnswerOfUser(String eid) {

        //          eid    eTid
        //  通过 考试 ➡ 试卷 ➡ 答题卡表
        //      用户     ➡     答题卡
        //             uid

        //  通过 考试id 查询 试卷id
        Integer eTid = eTestService.findETestIdByEid(Integer.parseInt(eid));

        //  通过 试卷id 查询 所有未批改的答题信息
        List<Answer> answerList = answerService.findAnswerListByETid(eTid);

        //  判断是否 将所有答题信息批改完成
        if (answerList.size() == 0) {

            //  批改完成，修改试卷的status为1
            Integer i = eTestService.changeStatus(eTid);
            //  试卷状态修改是否成功
            if (i != 1) {
                //  修改失败
                return new JsonResult(0, "阅卷完成，但是试卷状态修改失败！");
            }

            //  阅卷完成且试卷状态修改成功
            return new JsonResult(88, "阅卷完成！");
        }

        //  还未批改完成，继续返回答题信息的 list
        return new JsonResult(1, answerList);
    }

    /**
     *          为用户修改总成绩
     *
     * @Author  imlee
     * @param uid
     * @param score
     * @return
     */
    @RequestMapping("/giveScore.do")
    public JsonResult giveScore(String eTid, String uid, String score) {

        //  修改成绩
        Integer i = answerService.giveScore(Integer.parseInt(eTid), Integer.parseInt(uid), Integer.parseInt(score));
        //  判断修改成绩是否成功
        if (i != 1) {
            return new JsonResult(0, "成绩添加失败！");
        }

        return new JsonResult(1, "成绩添加成功！");
    }

}
