package com.qfedu.examsys.pojo;

import java.util.Date;

public class Answer {
    private Integer id;

    private Integer uid;

    private Integer etid;

    private Integer score;

    private Date createtime;

    private String judges;

    private String shorts;

    private String radios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getEtid() {
        return etid;
    }

    public void setEtid(Integer etid) {
        this.etid = etid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getJudges() {
        return judges;
    }

    public void setJudges(String judges) {
        this.judges = judges == null ? null : judges.trim();
    }

    public String getShorts() {
        return shorts;
    }

    public void setShorts(String shorts) {
        this.shorts = shorts == null ? null : shorts.trim();
    }

    public String getRadios() {
        return radios;
    }

    public void setRadios(String radios) {
        this.radios = radios == null ? null : radios.trim();
    }
}