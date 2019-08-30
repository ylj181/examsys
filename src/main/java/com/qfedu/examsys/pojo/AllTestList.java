package com.qfedu.examsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Lei
 * @Date 2019-8-30 16:20
 */

//简单 选择 单选 VO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllTestList {

    private List<Radio> radioList;

    private  List<Judge> judgeList;

    private  List<ShortAnswer> answerList;



}
