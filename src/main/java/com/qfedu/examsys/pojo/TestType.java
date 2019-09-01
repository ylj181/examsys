package com.qfedu.examsys.pojo;

import java.util.Date;

public class TestType {
    private Integer id;

    private Integer uid;

    private String p_section_remarks;

    private String p_dbids;

    private String p_qtypes;

    private String p_levels;

    private String p_qnums;

    private String p_section_names;

    private Date starttime;

    private Date begintime;

    private String p_name;

    private String p_duration;

    private String eTestId;

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

    public String getP_section_remarks() {
        return p_section_remarks;
    }

    public void setP_section_remarks(String p_section_remarks) {
        this.p_section_remarks = p_section_remarks;
    }

    public String getP_dbids() {
        return p_dbids;
    }

    public void setP_dbids(String p_dbids) {
        this.p_dbids = p_dbids;
    }

    public String getP_qtypes() {
        return p_qtypes;
    }

    public void setP_qtypes(String p_qtypes) {
        this.p_qtypes = p_qtypes;
    }

    public String getP_levels() {
        return p_levels;
    }

    public void setP_levels(String p_levels) {
        this.p_levels = p_levels;
    }

    public String getP_qnums() {
        return p_qnums;
    }

    public void setP_qnums(String p_qnums) {
        this.p_qnums = p_qnums;
    }

    public String getP_section_names() {
        return p_section_names;
    }

    public void setP_section_names(String p_section_names) {
        this.p_section_names = p_section_names;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_duration() {
        return p_duration;
    }

    public void setP_duration(String p_duration) {
        this.p_duration = p_duration;
    }

    public String geteTestId() {
        return eTestId;
    }

    public void seteTestId(String eTestId) {
        this.eTestId = eTestId;
    }
}