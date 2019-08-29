package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.EnrollDao;
import com.qfedu.examsys.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollServiceImpl implements EnrollService {

    @Autowired(required = false)
    private EnrollDao enrollDao;

    @Override
    public int addEnrollSubject(Integer subjectId) {
        return 0;
    }
}
