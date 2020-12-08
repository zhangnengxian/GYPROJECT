package cc.dfsoft.uexpress.biz.sys.dept.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 操作日志
 * @author cui
 *
 */
@Entity
@Table(name = "operate_log")
public class OperateLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3507133202324765348L;
	private String operateLogId;
	private String staffId;
	private String robotCode;
	private String operateType;
	private Date operateTime;
	private String xaxis;
	private String yaxis;
	private String deptId;
	private String isMobile;
	
	@Id
	@Column(name = "operate_Log_Id")
	public String getOperateLogId() {
		return operateLogId;
	}
	public void setOperateLogId(String operateLogId) {
		this.operateLogId = operateLogId;
	}
	
	@Column(name = "staff_Id")
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	@Column(name = "robot_Code")
	public String getRobotCode() {
		return robotCode;
	}
	public void setRobotCode(String robotCode) {
		this.robotCode = robotCode;
	}
	
	@Column(name = "operate_Type")
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operate_Time")
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "xaxis")
	public String getXaxis() {
		return xaxis;
	}
	public void setXaxis(String xaxis) {
		this.xaxis = xaxis;
	}
	
	@Column(name = "yaxis")
	public String getYaxis() {
		return yaxis;
	}
	public void setYaxis(String yaxis) {
		this.yaxis = yaxis;
	}
	
	@Column(name = "dept_Id")
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "is_Mobile")
	public String getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

}