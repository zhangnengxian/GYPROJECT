package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;



/**
 *@ Description: 施工任务单信息（鸿巨）
 *@ author: zhangnx
 *@ String: 2019/11/22 15:24
 *@ Version:1.0
 * */
public class ConstructionInfo {
    private String conNo;				//合同号
    private String projTimeLimit;		//工期
    private String cpArriveDate;			//计划下达时间
    private String builder;				//现场代表
    private String bulTel;				//现场代表电话
    private String cpDocumenter;		//编制人
    private String household;			//户数(合同表中取)
    private String remark;				//备注
    private String designDrawingNo;		//设计图号(设计表中取)
    private String cuName;				//施工单位名称
    private String cuPm;				//项目经理
    private String managementQae;		//施工员
    private String documenter;		//资料员
    private String managementWacf;		//材料员
    private String technician;			//质量技术员
    private String saftyOfficer;		//安全员
    private String testLeader;			//班组长
    private String welder;				//焊工
    private String suName;				//监理单位
    private String suCse;				//总监理工程师
    private String suJgj;				//现场监理工程师

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }

    public String getProjTimeLimit() {
        return projTimeLimit;
    }

    public void setProjTimeLimit(String projTimeLimit) {
        this.projTimeLimit = projTimeLimit;
    }

    public String getCpArriveDate() {
        return cpArriveDate;
    }

    public void setCpArriveDate(String cpArriveDate) {
        this.cpArriveDate = cpArriveDate;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getBulTel() {
        return bulTel;
    }

    public void setBulTel(String bulTel) {
        this.bulTel = bulTel;
    }

    public String getCpDocumenter() {
        return cpDocumenter;
    }

    public void setCpDocumenter(String cpDocumenter) {
        this.cpDocumenter = cpDocumenter;
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDesignDrawingNo() {
        return designDrawingNo;
    }

    public void setDesignDrawingNo(String designDrawingNo) {
        this.designDrawingNo = designDrawingNo;
    }

    public String getCuName() {
        return cuName;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    public String getCuPm() {
        return cuPm;
    }

    public void setCuPm(String cuPm) {
        this.cuPm = cuPm;
    }

    public String getManagementQae() {
        return managementQae;
    }

    public void setManagementQae(String managementQae) {
        this.managementQae = managementQae;
    }

    public String getDocumenter() {
        return documenter;
    }

    public void setDocumenter(String documenter) {
        this.documenter = documenter;
    }

    public String getManagementWacf() {
        return managementWacf;
    }

    public void setManagementWacf(String managementWacf) {
        this.managementWacf = managementWacf;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getSaftyOfficer() {
        return saftyOfficer;
    }

    public void setSaftyOfficer(String saftyOfficer) {
        this.saftyOfficer = saftyOfficer;
    }

    public String getTestLeader() {
        return testLeader;
    }

    public void setTestLeader(String testLeader) {
        this.testLeader = testLeader;
    }

    public String getWelder() {
        return welder;
    }

    public void setWelder(String welder) {
        this.welder = welder;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getSuCse() {
        return suCse;
    }

    public void setSuCse(String suCse) {
        this.suCse = suCse;
    }

    public String getSuJgj() {
        return suJgj;
    }

    public void setSuJgj(String suJgj) {
        this.suJgj = suJgj;
    }

    @Override
    public String toString() {
        return "ConstructionInfo{" +
                "conNo='" + conNo + '\'' +
                ", projTimeLimit='" + projTimeLimit + '\'' +
                ", cpArriveDate=" + cpArriveDate +
                ", builder='" + builder + '\'' +
                ", bulTel='" + bulTel + '\'' +
                ", cpDocumenter='" + cpDocumenter + '\'' +
                ", household='" + household + '\'' +
                ", remark='" + remark + '\'' +
                ", designDrawingNo='" + designDrawingNo + '\'' +
                ", cuName='" + cuName + '\'' +
                ", cuPm='" + cuPm + '\'' +
                ", managementQae='" + managementQae + '\'' +
                ", documenter='" + documenter + '\'' +
                ", managementWacf='" + managementWacf + '\'' +
                ", technician='" + technician + '\'' +
                ", saftyOfficer='" + saftyOfficer + '\'' +
                ", testLeader='" + testLeader + '\'' +
                ", welder='" + welder + '\'' +
                ", suName='" + suName + '\'' +
                ", suCse='" + suCse + '\'' +
                ", suJgj='" + suJgj + '\'' +
                '}';
    }
}
