package cc.dfsoft.project.biz.base.subpackage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 
 * 描述:智能表合同查询辅助类
 * @author liaoyq
 * @createTime 2017年9月19日
 */
public class IntelligentMeterContractReq extends PageSortReq {

	private String projId;
	private String projNo;
	private String projName;
	private String projAddr;
	private String sPartyName;
	private String imcNo;
	private String imcSignDateStart;
	private String imcSignDateEnd;
	private String isPrint;
	private String flag;
	private String projStatusId;
	private String stepId;
	private String projLtypeId;
	private Integer isIntelligentMeter;

	private String isPass;					//是否通过审核  1通过
	private String modifyStatus;			//null或3:无修改，0:修改审批中，1：修改审核已通过，2：修改审核未通过
	
	private String menuId;					//菜单ID

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getModifyStatus() {
		return modifyStatus;
	}

	public void setModifyStatus(String modifyStatus) {
		this.modifyStatus = modifyStatus;
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
	
	public String getsPartyName() {
		return sPartyName;
	}
	public void setsPartyName(String sPartyName) {
		this.sPartyName = sPartyName;
	}
	public String getImcSignDateStart() {
		return imcSignDateStart;
	}
	public void setImcSignDateStart(String imcSignDateStart) {
		this.imcSignDateStart = imcSignDateStart;
	}
	public String getImcSignDateEnd() {
		return imcSignDateEnd;
	}
	public void setImcSignDateEnd(String imcSignDateEnd) {
		this.imcSignDateEnd = imcSignDateEnd;
	}
	public String getImcNo() {
		return imcNo;
	}
	public void setImcNo(String imcNo) {
		this.imcNo = imcNo;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getProjLtypeId() {
		return projLtypeId;
	}
	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	public Integer getIsIntelligentMeter() {
		return isIntelligentMeter;
	}
	public void setIsIntelligentMeter(Integer isIntelligentMeter) {
		this.isIntelligentMeter = isIntelligentMeter;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
