package cc.dfsoft.uexpress.biz.sys.dept.dto;

import java.util.List;

/**
 * 员工登录信息
 * @author 1919
 *
 */
public class LoginInfo {

	/** 租户ID */
	private String tenantId;
	/** 公司ID */
	private String corpId;
	/** 部门ID */
	private String deptId;
	private String deptTransfer;
	/** 部门名称 */
	private String deptName;
	/** 员工ID */
	private String staffId;
	/** 员工姓名 */
	private String staffName;
	/** 员工职务 */
	private String post;
	/** 员工头像路径 */
	private String photoUrl;
	private String longitude;
	private String latitude;
	private String unitType;  //单位类型
	/**联系电话*/
	private String mobile;

	private Integer locationDuration;         //定位时长

	private String corpName;//分公司名称
	private String orgId;	//组织id
	private String deptDivide;	//部门划分
	private String businessType;//业务类型


	private String signPicture;//电子签名
	private List<String> deptIds;	//可查看受理工程的部门id集合

	private String belongCorpId;//所属公司id

	private String registrationId;//设备ID

	private String loginAccount;//登录人账号

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptTransfer() {
		return deptTransfer;
	}
	public void setDeptTransfer(String deptTransfer) {
		this.deptTransfer = deptTransfer;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getLocationDuration() {
		return locationDuration;
	}
	public void setLocationDuration(Integer locationDuration) {
		this.locationDuration = locationDuration;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDeptDivide() {
		return deptDivide;
	}
	public void setDeptDivide(String deptDivide) {
		this.deptDivide = deptDivide;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSignPicture() {
		return signPicture;
	}
	public void setSignPicture(String signPicture) {
		this.signPicture = signPicture;
	}


	public List<String> getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}
	public String getBelongCorpId() {
		return belongCorpId;
	}
	public void setBelongCorpId(String belongCorpId) {
		this.belongCorpId = belongCorpId;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}


}
