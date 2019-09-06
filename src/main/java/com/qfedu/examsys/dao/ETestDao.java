package com.qfedu.examsys.dao;

import com.qfedu.examsys.pojo.ETest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ETestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ETest record);

    int insertSelective(ETest record);

    ETest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ETest record);

    int updateByPrimaryKey(ETest record);

    @Select("select count(1) from eTest")
    int  getCount();

    //通过考场id  查询试卷 eTest
    ETest findByeid(Integer eid);

    /**
     * @Author imlee
     * @param eid
     * @return
     */
    public Integer findETestIdByEid(int eid);

    /**
     *          阅卷完成，修改试卷状态
     *
     * @Author  imlee
     * @param eTid  试卷ID
     * @return      受影响的行数
     */
    public Integer changeStatus(Integer eTid);



    List<ETest> findAlls(@Param("name") String name);
    List<ETest> findAlls();

    //通过试卷的Id查询试卷状态信息
    Integer findETestById(Integer id);
    // 根据id查
    ETest QueryById(Integer id);
}
