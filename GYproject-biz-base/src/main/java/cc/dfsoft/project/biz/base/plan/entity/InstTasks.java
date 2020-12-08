package cc.dfsoft.project.biz.base.plan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 安装任务
 */
@Entity
@Table(name = "INST_TASKS")
public class InstTasks implements Serializable {
	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = -3827932679359843757L;
	private String id;				//安装任务ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String remark;				//备注
	private String meterType;           //表具型号
	private String orderMan;            //下单人
	private Date orderDate;	            //下单时间



	/** default InstTasks */
	public InstTasks() {
	}

	@Id
	@Column(name = "ID", unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "METER_TYPE")
	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	@Column(name = "ORDER_MAN")
	public String getOrderMan() {
		return orderMan;
	}

	public void setOrderMan(String orderMan) {
		this.orderMan = orderMan;
	}

	@Column(name = "ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}