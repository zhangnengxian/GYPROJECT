package cc.dfsoft.project.biz.base.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SIGN_NOTICE")
public class SignNotice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -42644567332416567L;
	private String signNoticeId;      //主键id
	private String post;              //岗位
	private String postName;          //岗位名称
	private String status;            //签字状态
	private String dataType;          //单据类型
	private String dataTypeName;      //单据名称
	private String sortNo;            //签字顺序
	private String projId;            //工程id
	private String projNo;            //工程编号
	private String projName;          //工程名称
	private String signer;            //签字人
	private String businessOrderId;	   //业务单id
	
	private String menuId;			  //菜单id
	private String url;				  //连接
	
	private String grade;			  //报验审核级别，为了区分其他签字通知
	private String sendJpushStatus;		//发送消息状态 1 已发 其他未发
	@Id
	@Column(name = "SIGN_NOTICE_ID", unique = true)
	public String getSignNoticeId() {
		return signNoticeId;
	}
	public void setSignNoticeId(String signNoticeId) {
		this.signNoticeId = signNoticeId;
	}
	
	@Column(name = "POST", unique = true)
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	@Column(name = "STATUS", unique = true)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "DATA_TYPE", unique = true)
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Column(name = "SORT_NO", unique = true)
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	
	@Column(name = "PROJ_ID", unique = true)
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	@Column(name = "PROJ_NO", unique = true)
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	@Column(name = "POST_NAME")
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	@Column(name = "DATA_TYPE_NAME")
	public String getDataTypeName() {
		return dataTypeName;
	}
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}
	
	@Column(name = "SIGNER")
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
	}
	@Column(name = "BUSINESS_ORDER_ID")
	public String getBusinessOrderId() {
		return businessOrderId;
	}
	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}
	
	@Transient
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Transient
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "GRADE")
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Column(name="SEND_JPUSH_STATUS")
	public String getSendJpushStatus() {
		return sendJpushStatus;
	}

	public void setSendJpushStatus(String sendJpushStatus) {
		this.sendJpushStatus = sendJpushStatus;
	}
}
