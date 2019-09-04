package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.ETestDao;
import com.qfedu.examsys.pojo.ETest;
import com.qfedu.examsys.service.ETestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
