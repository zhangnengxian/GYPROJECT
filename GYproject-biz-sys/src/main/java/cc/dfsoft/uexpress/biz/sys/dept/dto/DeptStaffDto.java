package cc.dfsoft.uexpress.biz.sys.dept.dto;

import javax.persistence.Transient;

import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;

/**
 * 部门员工信息
 * @author 1919
 *
 */
public class DeptStaffDto {

	private String staffId;
	private String staffNo;
	private String staffName;
	private String loginAccount;
	private String phone;
	private String mobile;
	private String deptName;
	private String post;//职务
	
	private String postDes;//描述
	
	private String postName;//职务名称
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	
	public String getPostDes() {
		for(PostTypeEnum e: PostTypeEnum.values()) {
	   		if(e.getValue().equals(this.post)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setPostDes(String postDes) {
		this.postDes = postDes;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
}
