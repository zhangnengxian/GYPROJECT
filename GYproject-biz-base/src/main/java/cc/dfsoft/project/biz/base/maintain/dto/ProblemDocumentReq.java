package cc.dfsoft.project.biz.base.maintain.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * @Desc: 问题单据
 * @Auther: zhangnx
 * @Date: 2019/1/21 14:42
 * @Version:1.0
 */
public class ProblemDocumentReq extends PageSortReq {
    private String pdId;                         //主键ID
    private String corpId;                       //公司ID
    private String corpName;                    //公司名称
    private String menuId;                      //子菜单ID
    private String menuName;                    //子菜单名称
    private String level2MenuId;                //2级菜单ID(模块)
    private String level2MenuName;              //2级菜单名称(模块)
    private String problemTypeCode;            //问题类型名称
    private String problemTypeDesc;            //问题类型
    private String proposer;                    //问题提出人
    private String proposeTime;                   //问题提出日期
    private String problemStateCode;            //问题状态代码
    private String problemStateDesc;            //问题状态名称
    private String preprocessor;                //预处理人
    private String pretreatmentTime;              //预处理时间
    private String actualHandler;               //实际处理人
    private String actualProcessingTime;          //实际处理时间
    private String solution;                    //解决方案
    private String remarks;                     //备注
    private String emergencyLevelCode;         //紧急状况代码
    private String emergencyLevelDesc;         //紧急状况名称
    private String registrationTime;             //问题登记日期
    private String projNo;                      //工程编号
    private String registLtTime;                 //登记日期(小)
    private String registGtTime;                 //登记日期(大)
    private String preLtTime;                      //预处理日期(小)
    private String preGtTime;                      //预处理日期(大)
    private String actualLtTime;                   //实际处理日期(小)
    private String actualGtTime;                   //实际处理日期(大)
    private String commonField;                //搜索字段

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getLevel2MenuId() {
        return level2MenuId;
    }

    public void setLevel2MenuId(String level2MenuId) {
        this.level2MenuId = level2MenuId;
    }

    public String getLevel2MenuName() {
        return level2MenuName;
    }

    public void setLevel2MenuName(String level2MenuName) {
        this.level2MenuName = level2MenuName;
    }

    public String getProblemTypeCode() {
        return problemTypeCode;
    }

    public void setProblemTypeCode(String problemTypeCode) {
        this.problemTypeCode = problemTypeCode;
    }

    public String getProblemTypeDesc() {
        return problemTypeDesc;
    }

    public void setProblemTypeDesc(String problemTypeDesc) {
        this.problemTypeDesc = problemTypeDesc;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getProposeTime() {
        return proposeTime;
    }

    public void setProposeTime(String proposeTime) {
        this.proposeTime = proposeTime;
    }

    public String getProblemStateCode() {
        return problemStateCode;
    }

    public void setProblemStateCode(String problemStateCode) {
        this.problemStateCode = problemStateCode;
    }

    public String getProblemStateDesc() {
        return problemStateDesc;
    }

    public void setProblemStateDesc(String problemStateDesc) {
        this.problemStateDesc = problemStateDesc;
    }

    public String getPreprocessor() {
        return preprocessor;
    }

    public void setPreprocessor(String preprocessor) {
        this.preprocessor = preprocessor;
    }

    public String getPretreatmentTime() {
        return pretreatmentTime;
    }

    public void setPretreatmentTime(String pretreatmentTime) {
        this.pretreatmentTime = pretreatmentTime;
    }

    public String getActualHandler() {
        return actualHandler;
    }

    public void setActualHandler(String actualHandler) {
        this.actualHandler = actualHandler;
    }

    public String getActualProcessingTime() {
        return actualProcessingTime;
    }

    public void setActualProcessingTime(String actualProcessingTime) {
        this.actualProcessingTime = actualProcessingTime;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEmergencyLevelCode() {
        return emergencyLevelCode;
    }

    public void setEmergencyLevelCode(String emergencyLevelCode) {
        this.emergencyLevelCode = emergencyLevelCode;
    }

    public String getEmergencyLevelDesc() {
        return emergencyLevelDesc;
    }

    public void setEmergencyLevelDesc(String emergencyLevelDesc) {
        this.emergencyLevelDesc = emergencyLevelDesc;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getRegistLtTime() {
        return registLtTime;
    }

    public void setRegistLtTime(String registLtTime) {
        this.registLtTime = registLtTime;
    }

    public String getRegistGtTime() {
        return registGtTime;
    }

    public void setRegistGtTime(String registGtTime) {
        this.registGtTime = registGtTime;
    }

    public String getPreLtTime() {
        return preLtTime;
    }

    public void setPreLtTime(String preLtTime) {
        this.preLtTime = preLtTime;
    }

    public String getPreGtTime() {
        return preGtTime;
    }

    public void setPreGtTime(String preGtTime) {
        this.preGtTime = preGtTime;
    }

    public String getActualLtTime() {
        return actualLtTime;
    }

    public void setActualLtTime(String actualLtTime) {
        this.actualLtTime = actualLtTime;
    }

    public String getActualGtTime() {
        return actualGtTime;
    }

    public void setActualGtTime(String actualGtTime) {
        this.actualGtTime = actualGtTime;
    }

    public String getCommonField() {
        return commonField;
    }

    public void setCommonField(String commonField) {
        this.commonField = commonField;
    }
}


