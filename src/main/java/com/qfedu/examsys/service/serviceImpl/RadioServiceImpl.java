package com.qfedu.examsys.service.serviceImpl;

import com.qfedu.examsys.dao.RadioDao;
import com.qfedu.examsys.pojo.Radio;
import com.qfedu.examsys.service.RadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RadioServiceImpl implements RadioService {

    @Autowired(required = false)
    private RadioDao radioDao;

    @Override
    public void insertMany(List<Radio> radios) {
        radioDao.insertMany(radios);
    }
}
