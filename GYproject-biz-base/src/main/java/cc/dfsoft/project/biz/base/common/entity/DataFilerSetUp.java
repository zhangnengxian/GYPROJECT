package cc.dfsoft.project.biz.base.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据过滤配置表
 * @author fulw
 *
 */
@Entity
@Table(name = "DATA_FILTER_SET_UP")
public class DataFilerSetUp implements java.io.Serializable {
	
	
	private static final long serialVersionUID = -1087710880977718428L;
	
	private String dfsuId;				//主键id
	private String projectType;			//工程类型id
	private String projectTypeDes;		//工程类型
	private String contributionCode;	//出资方式id
	private String contributionModeDes;	//出资方式
	private String menuId;				//菜单id
	private String menuName;			//菜单名称
	private String corpId;				//分公司id
	private String queryDeptId;			//查询部门id
	private String queryDeptName;		//查询部门
	private String supSql;				//补充sql
	private String remark;				//备注
	
	public DataFilerSetUp() {
	}
	
	@Column(name = "PROJECT_TYPE")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Column(name = "PROJECT_TYPE_DES")
	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	@Column(name = "CONTRIBUTION_CODE")
	public String getContributionCode() {
		return contributionCode;
	}

	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}
	@Column(name = "CONTRIBUTION_CODE_DES")
	public String getContributionModeDes() {
		return contributionModeDes;
	}

	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
	}
	@Column(name = "MENU_ID")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Column(name = "MENU_NAME")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@Column(name = "QUERY_DEPT_ID")
	public String getQueryDeptId() {
		return queryDeptId;
	}

	public void setQueryDeptId(String queryDeptId) {
		this.queryDeptId = queryDeptId;
	}
	@Column(name = "QUERY_DEPT_NAME")
	public String getQueryDeptName() {
		return queryDeptName;
	}

	public void setQueryDeptName(String queryDeptName) {
		this.queryDeptName = queryDeptName;
	}
	@Column(name = "SUP_SQL")
	public String getSupSql() {
		return supSql;
	}

	public void setSupSql(String supSql) {
		this.supSql = supSql;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Id
	@Column(name = "DFSU_ID")
	public String getDfsuId() {
		return dfsuId;
	}

	public void setDfsuId(String dfsuId) {
		this.dfsuId = dfsuId;
	}

	
}
