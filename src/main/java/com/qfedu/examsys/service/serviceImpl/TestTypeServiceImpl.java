package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.TestTypeDao;
import com.qfedu.examsys.pojo.TestType;
import com.qfedu.examsys.service.testTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Lei
 * @Date 2019-8-31 16:33
 */

@Service
public class TestTypeServiceImpl implements testTypeService {

    @Autowired(required = false)
    private TestTypeDao testTypeDao;

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
}
