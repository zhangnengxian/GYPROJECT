package cc.dfsoft.project.biz.base.constructmanage.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 业务沟通查询条件Dto
 * @author Yuanyx
 *
 */
public class BusinessCommunicationReq extends PageSortReq{
	
	private String projId;				//工程id
	private String bcStatus;			//业务通知状态
	private String bcInitiatorId;		//发起人Id
	private String bcInitiatorName;		//发起人姓名
	private String noticeDateStart;		//通知日期开始
	private String noticeDateEnd;		//通知日期结束
	private String bcRecipientId;		//接受者Id
	private String bcRecipientName;		//接受者姓名
	private String bcRecipientDeptName;	//接受者部门名称
	private String replyDateStart;		//回复日期开始
	private String replyDateEnd;		//回复日期结束
	
	private String bcSendType;			//业务通知发送类型-页面列表显示1：发送，2：接收
	private String bcStatusDes;			//业务通知状态列表显示1：待通知，2：待回复，3：已回复
	
	private String bcTypeDetail;		//通知细类
	
	private String menuId;				//菜单ID
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getBcStatus() {
		return bcStatus;
	}

	public void setBcStatus(String bcStatus) {
		this.bcStatus = bcStatus;
	}

	public String getBcInitiatorId() {
		return bcInitiatorId;
	}

	public void setBcInitiatorId(String bcInitiatorId) {
		this.bcInitiatorId = bcInitiatorId;
	}

	public String getBcInitiatorName() {
		return bcInitiatorName;
	}

	public void setBcInitiatorName(String bcInitiatorName) {
		this.bcInitiatorName = bcInitiatorName;
	}

	public String getNoticeDateStart() {
		return noticeDateStart;
	}

	public void setNoticeDateStart(String noticeDateStart) {
		this.noticeDateStart = noticeDateStart;
	}

	public String getNoticeDateEnd() {
		return noticeDateEnd;
	}

	public void setNoticeDateEnd(String noticeDateEnd) {
		this.noticeDateEnd = noticeDateEnd;
	}

	public String getBcRecipientId() {
		return bcRecipientId;
	}

	public void setBcRecipientId(String bcRecipientId) {
		this.bcRecipientId = bcRecipientId;
	}

	public String getBcRecipientName() {
		return bcRecipientName;
	}

	public void setBcRecipientName(String bcRecipientName) {
		this.bcRecipientName = bcRecipientName;
	}

	public String getBcRecipientDeptName() {
		return bcRecipientDeptName;
	}

	public void setBcRecipientDeptName(String bcRecipientDeptName) {
		this.bcRecipientDeptName = bcRecipientDeptName;
	}

	public String getReplyDateStart() {
		return replyDateStart;
	}

	public void setReplyDateStart(String replyDateStart) {
		this.replyDateStart = replyDateStart;
	}

	public String getReplyDateEnd() {
		return replyDateEnd;
	}

	public void setReplyDateEnd(String replyDateEnd) {
		this.replyDateEnd = replyDateEnd;
	}

	public String getBcSendType() {
		return bcSendType;
	}

	public void setBcSendType(String bcSendType) {
		this.bcSendType = bcSendType;
	}

	public String getBcStatusDes() {
		return bcStatusDes;
	}

	public void setBcStatusDes(String bcStatusDes) {
		this.bcStatusDes = bcStatusDes;
	}

	public String getBcTypeDetail() {
		return bcTypeDetail;
	}

	public void setBcTypeDetail(String bcTypeDetail) {
		this.bcTypeDetail = bcTypeDetail;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
