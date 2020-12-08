package cc.dfsoft.project.biz.base.project.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;

/**
 * OperateRecord entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OPERATE_RECORD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OperateRecord implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = -642275219294863976L;

	private String orId;
	private String projId;
	private String projNo;
	private String stepId;
	private String stepName;
	private String operateDept1;
	private String operateCsr1;     //待办人账号ID,多个以逗号隔开
	private String operater;			//操作人
	private String operateDept2;
	private String operateCsr2;
	private String operateDept3;
	private String operateCsr3;
	private String operateDept4;
	private String operateCsr4;
	private String operateDept5;
	private String operateCsr5;
	private Date operateTime;
	private String remark;

	private String stepDes;				//步骤描述---------用于列表展示

	private String isValid;				//是否有效 1 有效 0 无效   

	private String projName;			//只读-工程名称
	private String corpId;				//分公司
	private String opType;				//类型
	private String grade;				//审核级别
	private String projectType;			//工程类型
	private String contributionMode;	//出资方式
	private String modifyStatus;		//状态状态   1已办 0 未办 2待办
	private String modifyStatusDes;		//只读
	private String owfId;				//操作流标准id
	private String businessOrderId;		//业务单id

	private String url;					//--只读属性
	private String menuId;				//--只读属性

	private String sendJpushStatus;		//发送消息状态 1 已发 其他未发

	// Constructors

	/** default constructor */
	public OperateRecord() {
	}


	// Property accessors
	@Id
	@Column(name = "OR_ID")
	public String getOrId() {
		return this.orId;
	}

	public void setOrId(String orId) {
		this.orId = orId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "STEP_ID")
	public String getStepId() {
		return this.stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	@Column(name = "STEP_NAME")
	public String getStepName() {
		return this.stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	@Column(name = "OPERATE_DEPT1")
	public String getOperateDept1() {
		return this.operateDept1;
	}

	public void setOperateDept1(String operateDept1) {
		this.operateDept1 = operateDept1;
	}

	@Column(name = "OPERATE_CSR1")
	public String getOperateCsr1() {
		return this.operateCsr1;
	}

	public void setOperateCsr1(String operateCsr1) {
		this.operateCsr1 = operateCsr1;
	}

	@Column(name = "OPERATE_DEPT2")
	public String getOperateDept2() {
		return this.operateDept2;
	}

	public void setOperateDept2(String operateDept2) {
		this.operateDept2 = operateDept2;
	}

	@Column(name = "OPERATE_CSR2")
	public String getOperateCsr2() {
		return this.operateCsr2;
	}

	public void setOperateCsr2(String operateCsr2) {
		this.operateCsr2 = operateCsr2;
	}

	@Column(name = "OPERATE_DEPT3")
	public String getOperateDept3() {
		return this.operateDept3;
	}

	public void setOperateDept3(String operateDept3) {
		this.operateDept3 = operateDept3;
	}

	@Column(name = "OPERATE_CSR3")
	public String getOperateCsr3() {
		return this.operateCsr3;
	}

	public void setOperateCsr3(String operateCsr3) {
		this.operateCsr3 = operateCsr3;
	}

	@Column(name = "OPERATE_DEPT4")
	public String getOperateDept4() {
		return this.operateDept4;
	}

	public void setOperateDept4(String operateDept4) {
		this.operateDept4 = operateDept4;
	}

	@Column(name = "OPERATE_CSR4")
	public String getOperateCsr4() {
		return this.operateCsr4;
	}

	public void setOperateCsr4(String operateCsr4) {
		this.operateCsr4 = operateCsr4;
	}

	@Column(name = "OPERATE_DEPT5")
	public String getOperateDept5() {
		return this.operateDept5;
	}

	public void setOperateDept5(String operateDept5) {
		this.operateDept5 = operateDept5;
	}

	@Column(name = "OPERATE_CSR5")
	public String getOperateCsr5() {
		return this.operateCsr5;
	}

	public void setOperateCsr5(String operateCsr5) {
		this.operateCsr5 = operateCsr5;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATE_TIME")
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getStepDes() {
		for(StepEnum e: StepEnum.values()) {
			if(e.getValue().equals(this.stepId)) {
				return e.getMessage();
			} else {
				for(StepOutWorkflowEnum stepOutWorkflowEnum : StepOutWorkflowEnum.values()) {
					if(stepOutWorkflowEnum.getValue().equals(this.stepId)) {
						return stepOutWorkflowEnum.getMessage();
					} 
				}
			}
		}
		return "";
	}

	public void setStepDes(String stepDes) {
		this.stepDes = stepDes;
	}


	@Column(name = "IS_VALID")
	public String getIsValid() {
		return isValid;
	}


	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Transient
	public String getProjName() {
		return projName;
	}


	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}


	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name = "OP_TYPE")
	public String getOpType() {
		return opType;
	}


	public void setOpType(String opType) {
		this.opType = opType;
	}

	@Column(name = "GRADE")
	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "PROJECT_TYPE")
	public String getProjectType() {
		return projectType;
	}


	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "CONTRIBUTION_MODE")
	public String getContributionMode() {
		return contributionMode;
	}


	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
	}

	@Column(name = "MODIFY_STATUS")
	public String getModifyStatus() {
		return modifyStatus;
	}


	public void setModifyStatus(String modifyStatus) {
		this.modifyStatus = modifyStatus;
	}

	@Transient
	public String getModifyStatusDes() {
		for(OperateWorkFlowEnum e: OperateWorkFlowEnum.values()) {
			if(e.getValue().equals(this.modifyStatus)) {
				return e.getMessage();
			}
		}
		return "";
	}


	public void setModifyStatusDes(String modifyStatusDes) {
		this.modifyStatusDes = modifyStatusDes;
	}

	@Column(name = "OWF_ID")
	public String getOwfId() {
		return owfId;
	}


	public void setOwfId(String owfId) {
		this.owfId = owfId;
	}

	@Column(name = "OPERATER")
	public String getOperater() {
		return operater;
	}


	public void setOperater(String operater) {
		this.operater = operater;
	}

	@Column(name = "BUSINESS_ORDER_ID")
	public String getBusinessOrderId() {
		return businessOrderId;
	}


	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}

	@Transient
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "SEND_JPUSH_STATUS")
	public String getSendJpushStatus() {
		return sendJpushStatus;
	}

	public void setSendJpushStatus(String sendJpushStatus) {
		this.sendJpushStatus = sendJpushStatus;
	}


	@Override
	public String toString() {
		return "OperateRecord [orId=" + orId + ", projId=" + projId + ", projNo=" + projNo + ", stepId=" + stepId
				+ ", stepName=" + stepName + ", operateDept1=" + operateDept1 + ", operateCsr1=" + operateCsr1
				+ ", operater=" + operater + ", operateDept2=" + operateDept2 + ", operateCsr2=" + operateCsr2
				+ ", operateDept3=" + operateDept3 + ", operateCsr3=" + operateCsr3 + ", operateDept4=" + operateDept4
				+ ", operateCsr4=" + operateCsr4 + ", operateDept5=" + operateDept5 + ", operateCsr5=" + operateCsr5
				+ ", operateTime=" + operateTime + ", remark=" + remark + ", stepDes=" + stepDes + ", isValid="
				+ isValid + ", projName=" + projName + ", corpId=" + corpId + ", opType=" + opType + ", grade=" + grade
				+ ", projectType=" + projectType + ", contributionMode=" + contributionMode + ", modifyStatus="
				+ modifyStatus + ", modifyStatusDes=" + modifyStatusDes + ", owfId=" + owfId + ", businessOrderId="
				+ businessOrderId + ", url=" + url + ", menuId=" + menuId + ", sendJpushStatus=" + sendJpushStatus
				+ "]";
	}
	
	
}