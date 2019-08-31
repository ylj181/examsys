package com.qfedu.examsys.service.serviceImpl;

import com.github.pagehelper.PageHelper;
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
    public List<Exam> findAllExams(Integer page,Integer limit) {

        PageHelper.startPage(page,limit);
        List<Exam> examList = examDao.findAllExams();

        return examList;
    }

    /**
     * 根据考试表的Id查询对应的考试信息
     * @param id  考试表的Id
     * @return  Exam对象
     */
    @Override
    public Exam findExamById(Integer id) {


        Exam exam = examDao.findExamById(id);

        return exam;
    }
}
