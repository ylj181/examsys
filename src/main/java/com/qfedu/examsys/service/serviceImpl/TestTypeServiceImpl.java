package com.qfedu.examsys.service.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.examsys.dao.ETestDao;
import com.qfedu.examsys.dao.TestTypeDao;
import com.qfedu.examsys.pojo.*;
import com.qfedu.examsys.service.testTypeService;
import com.qfedu.examsys.utils.WriteReadJson;
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

    @Autowired(required = false)
    private TestTypeDao testTypeDao;

    @Autowired
    private WriteReadJson writeReadJson;

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
        return testTypeDao.insertSelective(record);
    }

    @Override
    public TestType selectByPrimaryKey(Integer id) {
        return testTypeDao.selectByPrimaryKey(id);
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

        return  writeReadJson.ReadJson(s);
    }

    //根据testType主键 查找eTest信息
    @Override
    public List<ETest> findAlleTestByTId(Integer id) {
        List<ETest> eTests = new ArrayList<>();

        String s = testTypeDao.selectByPrimaryKey(id).geteTestId();

        String[] split = s.split(",");

        for (int i = 0; i < split.length; i++) {
            ETest eTest = eTestDao.selectByPrimaryKey(Integer.parseInt(split[i]));
            eTests.add(eTest);
        }

        return eTests;
    }

}
