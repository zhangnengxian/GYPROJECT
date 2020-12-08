package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;


/**
 *@ Description: 联合验收
 *@ author: zhangnx
 *@ Date: 2019/11/22 18:10
 *@ Version:1.0
 * */
public class JointAcceptanceInfo {
    private String jaId;                       //联合验收单id
    private String projNo;                     //项目编号
    private String jaClum;                     //联合验收验收结论
    private String jaDate;                       //联合验收验收时间

    public String getJaId() {
        return jaId;
    }

    public void setJaId(String jaId) {
        this.jaId = jaId;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getJaClum() {
        return jaClum;
    }

    public void setJaClum(String jaClum) {
        this.jaClum = jaClum;
    }

    public String getJaDate() {
        return jaDate;
    }

    public void setJaDate(String jaDate) {
        this.jaDate = jaDate;
    }

    @Override
    public String toString() {
        return "JointAcceptanceInfo{" +
                "jaId='" + jaId + '\'' +
                ", projNo='" + projNo + '\'' +
                ", jaClum='" + jaClum + '\'' +
                ", jaDate=" + jaDate +
                '}';
    }
}
