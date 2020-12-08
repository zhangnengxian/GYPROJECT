package cc.dfsoft.project.biz.base.maintain.entity;

import cc.dfsoft.project.biz.base.maintain.controller.CollectionUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @Desc: 问题单据
 * @Auther: zhangnx
 * @Date: 2019/1/21 14:42
 * @Version:1.0
 */
@Entity
@Table(name = "problem_document")
public class ProblemDocument{
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
    private Date proposeTime;                   //问题提出日期
    private String problemStateCode;            //问题状态代码
    private String problemStateDesc;            //问题状态名称
    private String preprocessor;                //预处理人
    private String preprocessorCN;               //预处理人
    private Date pretreatmentTime;              //预处理时间
    private String actualHandler;               //实际处理人
    private String actualHandlerCN;              //实际处理人
    private Date actualProcessingTime;          //实际处理时间
    private String solution;                    //解决方案
    private String remarks;                     //备注
    private String emergencyLevelCode;         //紧急状况代码
    private String emergencyLevelDesc;         //紧急状况名称
    private Date registrationTime;             //问题登记日期
    private String projNo;                      //工程编号
    private String editorField;                //富文本字段

    @Id
    @Column(name = "PD_ID", unique = true)
    public String getPdId() {
        return pdId;
    }

    @Column(name = "CORP_ID")
    public String getCorpId() {
        return corpId;
    }

    @Column(name = "MENU_ID")
    public String getMenuId() {
        return menuId;
    }

    @Column(name = "LEVEL_2_MENU_ID")
    public String getLevel2MenuId() {
        return level2MenuId;
    }

    @Column(name = "PROBLEM_TYPE_CODE")
    public String getProblemTypeCode() {
        return problemTypeCode;
    }

    @Column(name = "PROPOSER")
    public String getProposer() {
        return proposer;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PROPOSE_TIME")
    public Date getProposeTime() {
        return proposeTime;
    }

    @Column(name = "PROBLEM_STATE_CODE")
    public String getProblemStateCode() {
        return problemStateCode;
    }

    @Column(name = "PREPROCESSOR")
    public String getPreprocessor() {
        return preprocessor;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PRETREATMENT_TIME")
    public Date getPretreatmentTime() {
        return pretreatmentTime;
    }

    @Column(name = "ACTUAL_HANDLER")
    public String getActualHandler() {
        return actualHandler;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ACTUAL_PROCESSING_TIME")
    public Date getActualProcessingTime() {
        return actualProcessingTime;
    }

    @Column(name = "SOLUTION")
    public String getSolution() {
        return solution;
    }

    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    @Column(name = "EMERGENCY_LEVEL_CODE")
    public String getEmergencyLevelCode() {
        return emergencyLevelCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_TIME")
    public Date getRegistrationTime() {
        return registrationTime;
    }

    @Column(name = "PROJNO")
    public String getProjNo() {
        return projNo;
    }

    @Column(name = "EDITOR_FIELD")
    public String getEditorField() {
        return editorField;
    }

    @Transient
    public String getCorpName() {
        return corpName;
    }
    @Transient
    public String getMenuName() {
        return menuName;
    }
    @Transient
    public String getLevel2MenuName() {
        return level2MenuName;
    }
    @Transient
    public String getProblemTypeDesc() {
        return CollectionUtils.getDescByCode(this.problemTypeCode,"problem_document","problemType");
    }
    @Transient
    public String getProblemStateDesc() {
        return CollectionUtils.getDescByCode(this.problemStateCode,"problem_document","problemState");
    }
    @Transient
    public String getEmergencyLevelDesc() {
        return CollectionUtils.getDescByCode(this.emergencyLevelCode,"problem_document","emergencyLevel");
    }
    @Transient
    public String getPreprocessorCN() {
        return CollectionUtils.getDescByCode(this.preprocessor,"problem_document","handler");
    }
    @Transient
    public String getActualHandlerCN() {
        return CollectionUtils.getDescByCode(this.actualHandler,"problem_document","handler");
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setLevel2MenuId(String level2MenuId) {
        this.level2MenuId = level2MenuId;
    }

    public void setLevel2MenuName(String level2MenuName) {
        this.level2MenuName = level2MenuName;
    }

    public void setProblemTypeCode(String problemTypeCode) {
        this.problemTypeCode = problemTypeCode;
    }

    public void setProblemTypeDesc(String problemTypeDesc) {
        this.problemTypeDesc = problemTypeDesc;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public void setProposeTime(Date proposeTime) {
        this.proposeTime = proposeTime;
    }

    public void setProblemStateCode(String problemStateCode) {
        this.problemStateCode = problemStateCode;
    }

    public void setProblemStateDesc(String problemStateDesc) {
        this.problemStateDesc = problemStateDesc;
    }

    public void setPreprocessor(String preprocessor) {
        this.preprocessor = preprocessor;
    }

    public void setPretreatmentTime(Date pretreatmentTime) {
        this.pretreatmentTime = pretreatmentTime;
    }

    public void setActualHandler(String actualHandler) {
        this.actualHandler = actualHandler;
    }

    public void setActualProcessingTime(Date actualProcessingTime) {
        this.actualProcessingTime = actualProcessingTime;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setEmergencyLevelCode(String emergencyLevelCode) {
        this.emergencyLevelCode = emergencyLevelCode;
    }

    public void setEmergencyLevelDesc(String emergencyLevelDesc) {
        this.emergencyLevelDesc = emergencyLevelDesc;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public void setEditorField(String editorField) {
        this.editorField = editorField;
    }

}


