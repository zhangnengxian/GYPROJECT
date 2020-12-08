package cc.dfsoft.uexpress.biz.sys.dept.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ChangePasswordReq extends PageSortReq{
	
	private String staffId;			//员工Id
	private String oldPassword;		//旧密码
	private String newPassword;		//新密码
	private String confirmPassword;	//确认密码
	
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
}
