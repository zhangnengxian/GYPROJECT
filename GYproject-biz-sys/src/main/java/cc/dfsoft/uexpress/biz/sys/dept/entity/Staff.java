package cc.dfsoft.uexpress.biz.sys.dept.entity;

import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

/**
 * 员工信息
 * @author 1919
 *
 */
@Entity
@Table(name = "staff")
public class Staff implements java.io.Serializable {

	private static final long serialVersionUID = 5057774877108805873L;
	private String staffId;
	private String staffNo;
	private String staffName;
	private String idCardNo;
	private String post;
	private String addr;
	private String loginAccount;
	private String password;
	private String homePhone;
	private String phone;
	private String mobile;
	private String qq;
	private String wechat;
	private String email;
	private String createStaffId;
	private Date createTime;
	private String modifyStaffId;
	private Date modifyTime;
	private String corpId;
	private String deptId;
	private String deptTransfer;
	private String unitType;
	private String photoUrl;	//头像路径
	private String photoStr;	//base64字节
	private String postDes;//用于页面展示；

	private String postName;  //职务名称
	private String orgId;		//组织id
	private String tenantId;	//租户id

	private Clob signPictureUrl;	//签字路径
	private String signPicturePath; //签字图片
	private String signPictureStr;	//base64字节

	private String belongCorpId;	//所属分公司
	private String belongCorpName;	//所属分公司

	private String registrationId;//设备ID
	private String markOfDelet = "1";   //删除标记，1表示未删除，0表示已删除,默认是1

	
	
    @Column(name = "MARK_OF_DELETE")
	public String getMarkOfDelet() {
		return markOfDelet;
	}

	public void setMarkOfDelet(String markOfDelet) {
		this.markOfDelet = markOfDelet;
	}

	@Column(name = "REGISTRATIONID")
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	@Id
	@Column(name = "staff_id")
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "staff_no")
	public String getStaffNo() {
		return this.staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	@Column(name = "staff_name")
	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Column(name = "id_card_no")
	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	@Column(name = "post")
	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "addr")
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "login_account")
	public String getLoginAccount() {
		return this.loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "home_phone")
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "qq")
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "wechat")
	public String getWechat() {
		return this.wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "create_staff_id")
	public String getCreateStaffId() {
		return this.createStaffId;
	}

	public void setCreateStaffId(String createStaffId) {
		this.createStaffId = createStaffId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "modify_staff_id")
	public String getModifyStaffId() {
		return this.modifyStaffId;
	}

	public void setModifyStaffId(String modifyStaffId) {
		this.modifyStaffId = modifyStaffId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time")
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "corp_id")
	public String getCorpId() {
		return this.corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name = "dept_id")
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "DEPT_TRANSFER")
	public String getDeptTransfer() {
		return deptTransfer;
	}
	public void setDeptTransfer(String deptTransfer) {
		this.deptTransfer = deptTransfer;
	}


	@Column(name = "unit_type")
	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	@Transient
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
	@Column(name = "photo_url")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	@Transient
	public String getPhotoStr() {
		return photoStr;
	}

	public void setPhotoStr(String photoStr) {
		this.photoStr = photoStr;
	}

	@Column(name="POST_NAME")
	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	@Column(name="ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name="TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Column(name="SIGN_PICTURE_URL")
	public String getSignPictureUrl() {
		return ClobUtil.ClobToString(this.signPictureUrl);
	}

	public void setSignPictureUrl(String signPictureUrl) throws SerialException, SQLException {
		this.signPictureUrl = ClobUtil.stringToClob(signPictureUrl);
	}

	@Transient
	public String getSignPictureStr() {
		return signPictureStr;
	}

	public void setSignPictureStr(String signPictureStr) {
		this.signPictureStr = signPictureStr;
	}

	@Column(name="SIGN_PICTURE_PATH")
	public String getSignPicturePath() {
		return signPicturePath;
	}

	public void setSignPicturePath(String signPicturePath) {
		this.signPicturePath = signPicturePath;
	}

	@Column(name="BELONG_CORP_ID")
	public String getBelongCorpId() {
		return belongCorpId;
	}

	public void setBelongCorpId(String belongCorpId) {
		this.belongCorpId = belongCorpId;
	}

	@Column(name="BELONG_CORP_NAME")
	public String getBelongCorpName() {
		return belongCorpName;
	}

	public void setBelongCorpName(String belongCorpName) {
		this.belongCorpName = belongCorpName;
	}


}