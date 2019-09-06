package com.qfedu.examsys.service;

import com.qfedu.examsys.pojo.ETest;

import java.util.List;

/**
 * @Author Lei
 * @Date 2019-8-30 21:31
 */

public interface ETestService {
    int deleteByPrimaryKey(Integer id);

    int insert(ETest record);

    int insertSelective(ETest record);

    ETest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ETest record);

    int updateByPrimaryKey(ETest record);

    /**
     * @Author imlee
     * @param eid
     * @return
     */
    public Integer findETestIdByEid(int eid);

    List<ETest> findAlls(String name,Integer page, Integer limit);



    /**
     *          阅卷完成，修改试卷状态
     *
     * @Author  imlee
     * @param eTid  试卷ID
     * @return      受影响的行数
     */
    public Integer changeStatus(Integer eTid);

    //通过试卷的Id查询试卷状态信息
    Integer findETestById(Integer id);


    ETest QueryById(Integer id);

}
