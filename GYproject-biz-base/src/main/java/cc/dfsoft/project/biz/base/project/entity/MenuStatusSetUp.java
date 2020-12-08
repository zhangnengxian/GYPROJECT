package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author fuliwei
 *
 */
@Entity
@Table(name = "MENU_STATUS_SET_UP")
public class MenuStatusSetUp implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6521508736762034643L;
	
	private String msuId;				//主键id
	private String menuId;				//菜单id
	private String stepId;				//步骤id
	private String projStatusId;		//工程状态
	private String projectType;			//工程类型
	
	@Id
    @Column(name="MSU_ID", unique = true)
	public String getMsuId() {
		return msuId;
	}
	public void setMsuId(String msuId) {
		this.msuId = msuId;
	}
	
    @Column(name="MENU_ID")
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Column(name="STEP_ID")
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	@Column(name="PROJ_STATUS_ID")
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	@Column(name="PROJECT_TYPE")
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
}
