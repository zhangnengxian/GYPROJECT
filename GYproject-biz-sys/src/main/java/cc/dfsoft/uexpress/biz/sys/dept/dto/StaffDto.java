package cc.dfsoft.uexpress.biz.sys.dept.dto;

import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;

import java.sql.Clob;
import java.util.Date;
import java.util.List;


/**
 * 员工信息
 * @author 1919
 *
 */
public class StaffDto {

	/** 员工id */
	private String staffId;
	/** 员工号 */
	private String staffNo;
	/** 员工姓名 */
	private String staffName;
	/** 身份证号 */
	private String idCardNo;
	/** 职务 */
	private String post;
	/** 住址 */
	private String addr;
	/** 登录账号 */
	private String loginAccount;
	/** 登录密码 */
	private String password;
	/** 住宅电话 */
	private String homePhone;
	/** 办公电话*/
	private String phone;
	/** 手机*/
	private String mobile;
	/** QQ*/
	private String qq;
	/** 微信号*/
	private String wechat;
	/** 电子邮件*/
	private String email;
	/** 创建人*/
	private String createStaffId;
	/** 创建时间*/
	private Date createTime;
	/** 修改人*/
	private String modifyStaffId;
	/** 所属公司id*/
	private String corpId;
	/** 所属部门id*/
	private String deptId;
	private String deptTransfer;
	private String deptTransferName;
	/** 租户id*/
	private String tenantId;
	/** 角色Ids*/
	private String roleIds;
	/** 从属单位类型*/
	private String unitType;
	/** 集团或者业务合作伙伴*/
	private String corpType;
	private String postDes;//职务描述
	private String photoStr;	//base64字节
	private String unitTypeDes;	//从属单位类型枚举
	private String photoUrl;	//头像路径
	private Clob photoStream;	//头像字节流
	private String postName;	//职务名称
	private String orgId;		//组织id
	private String noticeRoleIds;//通知角色
	
	private Clob signPictureUrl;	//签字路径
	private String signPictureStr;	//base64字节
	private String signPicturePath; //签字图片
	private String constructionQaeUrlPath; //签字图片路径（能正确显示图片的路径）
	private List<Staff> sign;		//签字相关数据
	
	private String belongCorpId;	//所属分公司
	private String belongCorpName;	//所属分公司
	private String markOfDelet;  //删除标记1未删除，0已删除

	private String grade;  //审核级别

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDeptTransfer() {
		return deptTransfer;
	}
	public void setDeptTransfer(String deptTransfer) {
		this.deptTransfer = deptTransfer;
	}
	public String getDeptTransferName() {
		return deptTransferName;
	}
	public void setDeptTransferName(String deptTransferName) {
		this.deptTransferName = deptTransferName;
	}
	public String getMarkOfDelet() {
		return markOfDelet;
	}
	public void setMarkOfDelet(String markOfDelet) {
		this.markOfDelet = markOfDelet;
	}
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
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
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
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreateStaffId() {
		return createStaffId;
	}
	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyStaffId() {
		return modifyStaffId;
	}
	public void setModifyStaffId(String modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
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
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getPostDes() {
		return postDes;
	}
	public void setPostDes(String postDes) {
		this.postDes = postDes;
	}
	@Override
	public String toString() {
		return "StaffDto [staffId=" + staffId + ", staffNo=" + staffNo + ", staffName=" + staffName + ", idCardNo="
				+ idCardNo + ", post=" + post + ", addr=" + addr + ", loginAccount=" + loginAccount + ", password="
				+ password + ", homePhone=" + homePhone + ", phone=" + phone + ", mobile=" + mobile + ", qq=" + qq
				+ ", wechat=" + wechat + ", email=" + email + ", createStaffId=" + createStaffId + ", modifyStaffId="
				+ modifyStaffId + ", corpId=" + corpId + ", deptId=" + deptId + ", tenantId=" + tenantId + "]";
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getUnitType() {
		return this.unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getUnitTypeDes() {
		for(UnitTypeEnum enums : UnitTypeEnum.values()){
			if(enums.getValue().equals(this.unitType)){
				return enums.getMessage();
			}
		}
		return "";
	}
	public void setUnitTypeDes(String unitTypeDes) {
		this.unitTypeDes = unitTypeDes;
	}
	public String getCorpType() {
		return corpType;
	}
	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	public String getPhotoStr() {
		return photoStr;
	}
	public void setPhotoStr(String photoStr) {
		this.photoStr = photoStr;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Clob getPhotoStream() {
		return photoStream;
	}
	public void setPhotoStream(Clob photoStream) {
		this.photoStream = photoStream;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getNoticeRoleIds() {
		return noticeRoleIds;
	}
	public void setNoticeRoleIds(String noticeRoleIds) {
		this.noticeRoleIds = noticeRoleIds;
	}
	public Clob getSignPictureUrl() {
		return signPictureUrl;
	}
	public void setSignPictureUrl(Clob signPictureUrl) {
		this.signPictureUrl = signPictureUrl;
	}
	public String getSignPictureStr() {
		return signPictureStr;
	}
	public void setSignPictureStr(String signPictureStr) {
		this.signPictureStr = signPictureStr;
	}
	public String getSignPicturePath() {
		return signPicturePath;
	}
	public void setSignPicturePath(String signPicturePath) {
		this.signPicturePath = signPicturePath;
	}
	public String getConstructionQaeUrlPath() {
		return constructionQaeUrlPath;
	}
	public void setConstructionQaeUrlPath(String constructionQaeUrlPath) {
		this.constructionQaeUrlPath = constructionQaeUrlPath;
	}
	public String getBelongCorpId() {
		return belongCorpId;
	}
	public void setBelongCorpId(String belongCorpId) {
		this.belongCorpId = belongCorpId;
	}
	public String getBelongCorpName() {
		return belongCorpName;
	}
	public void setBelongCorpName(String belongCorpName) {
		this.belongCorpName = belongCorpName;
	}
	
	
	
}
