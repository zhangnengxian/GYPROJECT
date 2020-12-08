package cc.dfsoft.project.biz.base.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 打印报表版本控制
 * @author liaoyq
 * @createTime 2018年8月13日
 */
@Entity
@Table(name="REPORT_VERSION")
public class ReportVersion {

	private String rvId;		//RV_ID` varchar(30) NOT NULL COMMENT '主键ID',
	private String rvName;		//RV_NAME` varchar(50) DEFAULT NULL COMMENT '版本号',
	private Date rvDate;		//RV_DATE` datetime DEFAULT NULL COMMENT '版本日期',
	private String corpId;		//CORP_ID` varchar(30) DEFAULT NULL COMMENT '分公司ID',
	private String projType;	//PROJ_TYPE` varchar(30) DEFAULT NULL COMMENT '工程类型',
	private String menuId;	    //MENU_ID` varchar(30) DEFAULT NULL COMMENT '菜单ID',
	private String rvDes;		//描述
	
	public ReportVersion() {
		super();
	}
	@Id
	@Column(name="RV_ID")
	public String getRvId() {
		return rvId;
	}
	public void setRvId(String rvId) {
		this.rvId = rvId;
	}
	@Column(name="RV_NAME")
	public String getRvName() {
		return rvName;
	}
	public void setRvName(String rvName) {
		this.rvName = rvName;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="RV_DATE")
	public Date getRvDate() {
		return rvDate;
	}
	public void setRvDate(Date rvDate) {
		this.rvDate = rvDate;
	}
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@Column(name="PROJ_TYPE")
	public String getProjType() {
		return projType;
	}
	public void setProjType(String projType) {
		this.projType = projType;
	}
	@Column(name="MENU_ID")
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	@Column(name="RV_DES")
	public String getRvDes() {
		return rvDes;
	}
	public void setRvDes(String rvDes) {
		this.rvDes = rvDes;
	}
	
}
