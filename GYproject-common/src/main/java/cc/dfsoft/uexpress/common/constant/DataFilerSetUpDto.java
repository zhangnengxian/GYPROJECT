package cc.dfsoft.uexpress.common.constant;
/**
 * 数据过滤配置辅助类
 * @author liaoyq
 * @createTime 2018年8月22日
 */
public class DataFilerSetUpDto {
	
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
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getProjectTypeDes() {
		return projectTypeDes;
	}
	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	public String getContributionCode() {
		return contributionCode;
	}
	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}
	public String getContributionModeDes() {
		return contributionModeDes;
	}
	public void setContributionModeDes(String contributionModeDes) {
		this.contributionModeDes = contributionModeDes;
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
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getQueryDeptId() {
		return queryDeptId;
	}
	public void setQueryDeptId(String queryDeptId) {
		this.queryDeptId = queryDeptId;
	}
	public String getQueryDeptName() {
		return queryDeptName;
	}
	public void setQueryDeptName(String queryDeptName) {
		this.queryDeptName = queryDeptName;
	}
	public String getSupSql() {
		return supSql;
	}
	public void setSupSql(String supSql) {
		this.supSql = supSql;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
