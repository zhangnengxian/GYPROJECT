package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;

/**
 *@ Description: 工程基本信息（鸿巨）
 *@ author: zhangnx
 *@ Date: 2019/11/22 15:23
 *@ Version:1.0
 * */
public class ProjectInfo {

    private String projNo;						//工程编号
    private String projScaleDes;				//总体规模描述
    private String projectTypeDes;				//工程类型描述
    private String contributionModeDes;			//出资方式描述
    private String projName;					//工程名称
    private String projAddr;					//工程地点
    private String projStatusDes;				//状态-------只用于页面表格显示
    private String custName;					//客户名称(申请单位)
    private String custContact;					//客户联系人
    private String custPhone;					//客户联系电话
    private String corpId;		 				//分公司id
    private String corpName;		    		//分公司名称
    private String duName;						//设计单位名称
    private String designer;					//设计人

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getProjScaleDes() {
        return projScaleDes;
    }

    public void setProjScaleDes(String projScaleDes) {
        this.projScaleDes = projScaleDes;
    }

    public String getProjectTypeDes() {
        return projectTypeDes;
    }

    public void setProjectTypeDes(String projectTypeDes) {
        this.projectTypeDes = projectTypeDes;
    }

    public String getContributionModeDes() {
        return contributionModeDes;
    }

    public void setContributionModeDes(String contributionModeDes) {
        this.contributionModeDes = contributionModeDes;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    public String getProjStatusDes() {
        return projStatusDes;
    }

    public void setProjStatusDes(String projStatusDes) {
        this.projStatusDes = projStatusDes;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustContact() {
        return custContact;
    }

    public void setCustContact(String custContact) {
        this.custContact = custContact;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getDuName() {
        return duName;
    }

    public void setDuName(String duName) {
        this.duName = duName;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "projNo='" + projNo + '\'' +
                ", projScaleDes='" + projScaleDes + '\'' +
                ", projectTypeDes='" + projectTypeDes + '\'' +
                ", contributionModeDes='" + contributionModeDes + '\'' +
                ", projName='" + projName + '\'' +
                ", projAddr='" + projAddr + '\'' +
                ", projStatusDes='" + projStatusDes + '\'' +
                ", custName='" + custName + '\'' +
                ", custContact='" + custContact + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", corpId='" + corpId + '\'' +
                ", corpName='" + corpName + '\'' +
                ", duName='" + duName + '\'' +
                ", designer='" + designer + '\'' +
                '}';
    }
}
