package cc.dfsoft.project.biz.base.maintain.entity;

public class ResultInfo {
    private String code;
    private Object message;


    public ResultInfo(String code, Object message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }
}
