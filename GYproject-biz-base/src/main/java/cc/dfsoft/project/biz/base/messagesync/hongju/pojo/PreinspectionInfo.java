package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;

/**
 *@ Description: 联合验收
 *@ author: zhangnx
 *@ Date: 2019/11/22 18:10
 *@ Version:1.0
 * */
public class PreinspectionInfo {
    private String piId;			            //预验收ID
    private String projNo;                     //项目编号
    private String sirOpinion;		           //预验收验收意见
    private String piDate;			           //预验收预验日期

    public String getPiId() {
        return piId;
    }

    public void setPiId(String piId) {
        this.piId = piId;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getSirOpinion() {
        return sirOpinion;
    }

    public void setSirOpinion(String sirOpinion) {
        this.sirOpinion = sirOpinion;
    }

    public String getPiDate() {
        return piDate;
    }

    public void setPiDate(String piDate) {
        this.piDate = piDate;
    }

    @Override
    public String toString() {
        return "PreinspectionInfo{" +
                "piId='" + piId + '\'' +
                ", projNo='" + projNo + '\'' +
                ", sirOpinion='" + sirOpinion + '\'' +
                ", piDate=" + piDate +
                '}';
    }
}
