package com.qfedu.examsys.pojo;

import java.util.Date;

public class Exam {

    private Integer id;

    private Integer subjectid;

    private Date starttime;

    private Date endtime;

    private Subject subject;

    private ETest eTest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ETest geteTest() {
        return eTest;
    }

    public void seteTest(ETest eTest) {
        this.eTest = eTest;
    }
}