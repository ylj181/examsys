package com.qfedu.examsys.pojo;

public class ETest {
    private Integer id;

    private Integer eid;

    private Integer subjectid;

    private String name;

    private String judgejson;

    private String radiojson;

    private String shortanswerjson;

    private Subject subject;

    private Integer status;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJudgejson() {
        return judgejson;
    }

    public void setJudgejson(String judgejson) {
        this.judgejson = judgejson == null ? null : judgejson.trim();
    }

    public String getRadiojson() {
        return radiojson;
    }

    public void setRadiojson(String radiojson) {
        this.radiojson = radiojson == null ? null : radiojson.trim();
    }

    public String getShortanswerjson() {
        return shortanswerjson;
    }

    public void setShortanswerjson(String shortanswerjson) {
        this.shortanswerjson = shortanswerjson == null ? null : shortanswerjson.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}