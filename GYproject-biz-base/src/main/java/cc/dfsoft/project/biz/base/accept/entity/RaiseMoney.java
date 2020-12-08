package cc.dfsoft.project.biz.base.accept.entity;

import javax.persistence.*;
import java.util.Date;


/**
 * @ClassDesc: 提资信息
 * @author: zhangnx
 * @date: 10:59 2018/9/14
 * @version: V1.0
 */
@Entity
@Table(name = "raise_money")
public class RaiseMoney implements java.io.Serializable {
	//private static final long serialVersionUID = -8972767459576174326L;
	private String RaiseMoneyId;			//主键ID
	private String projId;					//工程id
	private String projNo;					//工程编号
	private String remark;			 		//申请备注
	private String customerResponseInfo;	//用户回复信息
	private Date applyDate;	            //申请日期
	private String uploadFlag;         		//是否上传资料
	private String isBack;					//是否退单
	private String backReason;				//退单原因
	private String backRemarks;				//退单备注
	
	private Date custReposeDate;			//用户回复时间
	
	private String stepId;						//步骤ID

	@Id
	@Column(name = "RAISEMONEY_ID", unique = true, nullable = false )
	public String getRaiseMoneyId() {
		return RaiseMoneyId;
	}

	public void setRaiseMoneyId(String raiseMoneyId) {
		RaiseMoneyId = raiseMoneyId;
	}

	@Basic
	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Basic
	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Basic
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Basic
	@Column(name = "CUSTOMER_RESPONSE_INFO")
	public String getCustomerResponseInfo() {
		return customerResponseInfo;
	}

	public void setCustomerResponseInfo(String customerResponseInfo) {
		this.customerResponseInfo = customerResponseInfo;
	}

	@Basic
	@Column(name = "APPLAY_DATE")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@Basic
	@Column(name = "UPLOADFLAG")
	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	
	@Transient
	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	@Transient
	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	@Transient
	public String getBackRemarks() {
		return backRemarks;
	}

	public void setBackRemarks(String backRemarks) {
		this.backRemarks = backRemarks;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CUST_REPOSE_DATE")
	public Date getCustReposeDate() {
		return custReposeDate;
	}

	public void setCustReposeDate(Date custReposeDate) {
		this.custReposeDate = custReposeDate;
	}

	@Transient
	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
}
