package cc.dfsoft.project.biz.base.contract.dto;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 辅助类
 * @author Administrator
 *
 */
public class ContractReviewDto extends PageSortReq{
	/**合同评审表主键ID*/
	private String crId;
	/**工程主键ID*/
	private String projId;
	/**工程编号*/
    private String projNo;
    /**合同编号*/
    private String conNo;
    /**工程名称*/
    private String projName;
    /**工程地点*/
    private String projAddr;
    /**工程类型*/
    private String projectTypeDes;
    /**合同类型,'1'表示买卖合同，‘2’工程合同，‘3’经济合同，‘4’租赁合同，‘5’劳务合同，‘6’服务合同，‘7’运输合同，‘8’承包合同，‘9’其他合同'*/
    private String contractType;
    /**合同概述*/
    private String contractSummary;
    /**经办人Id*/
    private String operatorId;
    /**经办人名字*/
    private String operator;
    /**删除状态,'0'是删除，'1'是未删除*/
    private String delStatus;  //默认是未删除
    /**删除人Id*/
    private String delPersonal;
    /**删除时间*/
    private Date delTime;
    /**版本号*/
    private int version;
    /**是否推送标志*/
    private String isPush;
     /**打印标志,0表示未打印，1表示已打印，默认是0打印*/
    private String isPrint;
   /**推送时间开始*/
    private String pushTimeStart;
    /**推送时间结束*/
    private String pushTimeEnd;
    /**菜单ID*/
    private String menuId;
	public String getCrId() {
		return crId;
	}
	public void setCrId(String crId) {
		this.crId = crId;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getProjectTypeDes() {
		return projectTypeDes;
	}
	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractSummary() {
		return contractSummary;
	}
	public void setContractSummary(String contractSummary) {
		this.contractSummary = contractSummary;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
	public String getDelPersonal() {
		return delPersonal;
	}
	public void setDelPersonal(String delPersonal) {
		this.delPersonal = delPersonal;
	}
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getIsPush() {
		return isPush;
	}
	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getPushTimeStart() {
		return pushTimeStart;
	}
	public void setPushTimeStart(String pushTimeStart) {
		this.pushTimeStart = pushTimeStart;
	}
	public String getPushTimeEnd() {
		return pushTimeEnd;
	}
	public void setPushTimeEnd(String pushTimeEnd) {
		this.pushTimeEnd = pushTimeEnd;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}  
  		

}
