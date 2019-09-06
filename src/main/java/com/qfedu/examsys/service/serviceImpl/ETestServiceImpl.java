package com.qfedu.examsys.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qfedu.examsys.dao.ETestDao;
import com.qfedu.examsys.pojo.ETest;
import com.qfedu.examsys.service.ETestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lei
 * @Date 2019-8-30 21:32
 */

@Service
public class ETestServiceImpl implements ETestService {

    @Autowired(required = false)
    private ETestDao eTestDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(ETest record) {
        return 0;
    }

    @Override
    public int insertSelective(ETest record) {
        return 0;
    }

    @Override
    public ETest selectByPrimaryKey(Integer id) {
        return eTestDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ETest record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ETest record) {
        return 0;
    }

    @Override
    public Integer findETestIdByEid(int eid) {
        return eTestDao.findETestIdByEid(eid);
    }

    @Override
    public Integer changeStatus(Integer eTid) {
        return eTestDao.changeStatus(eTid);
    }

    /**
     * 通过试卷的Id查询试卷状态信息
     * @param id   试卷Id
     * @return  返回试卷状态
     */
    @Override
    public Integer findETestById(Integer id) {

        Integer status = eTestDao.findETestById(id);
        return status;
    }

    @Override
    public ETest QueryById(Integer id) {
        return eTestDao.QueryById(id);
    }

    @Override
    public List<ETest> findAlls( String name,Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
         return eTestDao.findAlls(name);
    }

}
