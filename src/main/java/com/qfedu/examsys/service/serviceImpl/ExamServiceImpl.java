package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.ExamDao;
import com.qfedu.examsys.pojo.Exam;
import com.qfedu.examsys.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired(required = false)
    private ExamDao examDao;

    @Override
    public List<Exam> findAllExams() {
        return examDao.findAllExams();
    }
}
