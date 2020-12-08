package cc.dfsoft.uexpress.biz.sys.dept.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 员工查询请求参数
 * @author 1919
 *
 */
public class StaffQueryReq extends PageSortReq{

	private String staffName;
	
	/** 登录账号 */
	private String loginAccount;
	
	private String post;
	
	private String deptId;
	/**燃气集团或者业务合作伙伴*/
	private String corpType;
	
	private String unitType;
	
	private String staffNo;
	
	private String postName;
	
	private String corpId;
	
	private String unitName;
	
	private String menuId;
	private String belongCorpId;	//所属分公司
	private String markOfDelet;   //删除标记
	
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getBelongCorpId() {
		return belongCorpId;
	}

	public void setBelongCorpId(String belongCorpId) {
		this.belongCorpId = belongCorpId;
	}

	public String getMarkOfDelet() {
		return markOfDelet;
	}

	public void setMarkOfDelet(String markOfDelet) {
		this.markOfDelet = markOfDelet;
	}
	
}
