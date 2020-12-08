package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;


/**
 *@ Description: 联合验收
 *@ author: zhangnx
 *@ Date: 2019/11/22 18:10
 *@ Version:1.0
 * */
public class SelfInspectionListInfo {
    private String silId;				//自检单ID
    private String projNo;              //项目编号
    private String projManagerOpinion;	//项目自检   项目经理意见  --由项目经理意见改为--施工单位意见
    private String pushDate;				//项目自检 推送日期，用于为竣工报告指定默认时间

    public String getSilId() {
        return silId;
    }

    public void setSilId(String silId) {
        this.silId = silId;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getProjManagerOpinion() {
        return projManagerOpinion;
    }

    public void setProjManagerOpinion(String projManagerOpinion) {
        this.projManagerOpinion = projManagerOpinion;
    }

    public String getPushDate() {
        return pushDate;
    }

    public void setPushDate(String pushDate) {
        this.pushDate = pushDate;
    }

    @Override
    public String toString() {
        return "SelfInspectionListInfo{" +
                "silId='" + silId + '\'' +
                ", projNo='" + projNo + '\'' +
                ", projManagerOpinion='" + projManagerOpinion + '\'' +
                ", pushDate=" + pushDate +
                '}';
    }
}
