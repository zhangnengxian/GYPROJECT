package cc.dfsoft.project.biz.base.baseinfo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.baseinfo.enums.FestivalTypeEnum;
import cc.dfsoft.project.biz.base.contract.enums.ModifyStatusEnum;
/**
 * 节假日配置表
 * @author liaoyq
 * @createTime 2018年5月7日
 */
@Entity
@Table(name="FESTIVAL_SET")
public class Festival implements Serializable{

	private static final long serialVersionUID = 780465876604209717L;
	private String id;						//自增主键
	private Date festivalStartDate;			//节假日、加班开始时间
	private Date festivalEndDate;			//结束时间
	private String festivalName;			//假日名称
	private String dayType;					//日期类型：1-节假日，2-补班，3-其他
	private String orgId;					//公司ID
	private String isValid;					//是否可用：0-不可用，1-可用
	private String isDel;					//删除标记，0-未删除，1-已删除
	private Date operateTime ;				//操作日期
	private String operator;				//操作人
	
	private String dayTypeDes;				//类型描述-只读
	public Festival() {
		super();
	}
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="FESTIVAL_START_DATE")
	@Temporal(TemporalType.DATE)
	public Date getFestivalStartDate() {
		return festivalStartDate;
	}
	public void setFestivalStartDate(Date festivalStartDate) {
		this.festivalStartDate = festivalStartDate;
	}
	@Column(name="FESTIVAL_END_DATE")
	@Temporal(TemporalType.DATE)
	public Date getFestivalEndDate() {
		return festivalEndDate;
	}
	public void setFestivalEndDate(Date festivalEndDate) {
		this.festivalEndDate = festivalEndDate;
	}
	@Column(name="FESTIVAL_NAME")
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	@Column(name="DAY_TYPE")
	public String getDayType() {
		return dayType;
	}
	public void setDayType(String dayType) {
		this.dayType = dayType;
	}
	@Column(name="ORG_ID")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name="IS_VALID")
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	@Column(name="IS_DEL")
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	@Column(name="OPERATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	@Column(name="OPERATOR")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Transient
	public String getDayTypeDes() {
		if(null==this.dayType||""==this.dayType){
			return FestivalTypeEnum.OTHER.getMessage();
		}else {
			for(FestivalTypeEnum e: FestivalTypeEnum.values()) {
		   		if(e.getValue().equals(this.dayType)) {
		   			return e.getMessage();
		   		}
		   	}
			return "";
		}
	}
	public void setDayTypeDes(String dayTypeDes) {
		this.dayTypeDes = dayTypeDes;
	}
	  
	  
}
