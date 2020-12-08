package cc.dfsoft.uexpress.biz.sys.dept.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 部门查询请求参数
 * @author 1919
 *
 */
public class DepartmentReq extends PageSortReq{

	/** 单位类型 */
	private String deptType;
	/** 部门名称 */
	private String deptName;
	/** 部门ID*/
	private String allDesign;
	//递归路径
	private String deptPath;
	//部门
	private String deptId;

	public DepartmentReq() {
	}

	public DepartmentReq(String deptType) {
		this.deptType = deptType;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAllDesign() {
		return allDesign;
	}

	public void setAllDesign(String allDesign) {
		this.allDesign = allDesign;
	}

	public String getDeptPath() {
		return deptPath;
	}

	public void setDeptPath(String deptPath) {
		this.deptPath = deptPath;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
