package com.qfedu.examsys.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// 对多数据导入处理
@JsonIgnoreProperties(ignoreUnknown = true)
public class Judge {
    private Integer id;

    private String title;

    private String answer;

    private Integer subjectId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}