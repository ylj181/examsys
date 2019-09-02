package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.SubjectDao;
import com.qfedu.examsys.pojo.Subject;
import com.qfedu.examsys.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired(required = false)
    private SubjectDao subjectDao;


    /**
     * 查询所有的学科
     * @return  返回学科集合
     */
    @Override
    public List<Subject> findAllSubject() {
        return subjectDao.findAllSubject();
    }

    @Override
    public List<Subject> findSub() {
        List<Subject> list = subjectDao.findSub();
        return list;
    }


}
