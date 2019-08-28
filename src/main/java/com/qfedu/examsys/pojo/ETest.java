package pojo;

public class ETest {
    private Integer id;

    private Integer subjectid;

    private String name;

    private Integer radioid;

    private Integer judgeid;

    private Integer shortanswerid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRadioid() {
        return radioid;
    }

    public void setRadioid(Integer radioid) {
        this.radioid = radioid;
    }

    public Integer getJudgeid() {
        return judgeid;
    }

    public void setJudgeid(Integer judgeid) {
        this.judgeid = judgeid;
    }

    public Integer getShortanswerid() {
        return shortanswerid;
    }

    public void setShortanswerid(Integer shortanswerid) {
        this.shortanswerid = shortanswerid;
    }
}