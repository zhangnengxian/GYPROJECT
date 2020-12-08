package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 退回菜单配置
 * @author fuliwei
 *
 */
@Entity
@Table(name = "MENU_BACK_SET")
public class MenuBackSet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1293335685368878628L;
	
	
	private String mbsId;				//主键id
	private String projectType;			//工程类型
	private String contributionCode;	//出资方式
	private String projectTypeDes;		//工程类型描述
	private String menuId;				//菜单ID
	private String backStepId;			//可退回步骤id
	private String menuDes;				//菜单描述
	private String backStepDes;			//步骤描述
	private String flag;				//审核标志：1-需要胡部长审核
	private String isAudit;			    //是否 需要审核
	private String corpId;				//分公司ID
	private Date updateTime;//更新时间
	private String updateUser;//更新人
	private String corpName;//

	@Column(name = "UPDATE_USER")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Id
    @Column(name="MBS_ID", unique = true)
	public String getMbsId() {
		return mbsId;
	}
	public void setMbsId(String mbsId) {
		this.mbsId = mbsId;
	}
	@Column(name="PROJECT_TYPE")
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	@Column(name="CONTRIBUTION_CODE")
	public String getContributionCode() {
		return contributionCode;
	}
	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}
	@Column(name="PROJECT_TYPE_DES")
	public String getProjectTypeDes() {
		return projectTypeDes;
	}
	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	@Column(name="MENU_ID")
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Column(name="BACK_STEP_ID")
	public String getBackStepId() {
		return backStepId;
	}
	public void setBackStepId(String backStepId) {
		this.backStepId = backStepId;
	}
	@Column(name="MENU_DES")
	public String getMenuDes() {
		return menuDes;
	}
	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}
	@Column(name="BACK_STEP_DES")
	public String getBackStepDes() {
		return backStepDes;
	}
	public void setBackStepDes(String backStepDes) {
		this.backStepDes = backStepDes;
	}
	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name="IS_AUDIT")
	public String getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Transient
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
}
