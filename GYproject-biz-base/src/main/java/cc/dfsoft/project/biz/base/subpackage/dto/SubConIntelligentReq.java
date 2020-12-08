package cc.dfsoft.project.biz.base.subpackage.dto;

import java.util.List;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 
 * 描述:智能表合同查询辅助类
 * @author liaoyq
 * @createTime 2017年9月19日
 */
public class SubConIntelligentReq extends PageSortReq {

	private String projId;
	private String projNo;
	private String projName;
	private String projAddr;
	private String sPartyName;
	private String itScNo;
	private String signDateStart;
	private String signDateEnd;
	private String projStatusId;
	private String stepId;
	private String projLtypeId;
	private String isIntelligentMeter;
	private String projStatusIdStart;
	private String projStatusIdEnd;
	private List<String> projStuList;
	private String flag;
	
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
	public String getIsIntelligentMeter() {
		return isIntelligentMeter;
	}
	public void setIsIntelligentMeter(String isIntelligentMeter) {
		this.isIntelligentMeter = isIntelligentMeter;
	}
	public String getItScNo() {
		return itScNo;
	}
	public void setItScNo(String itScNo) {
		this.itScNo = itScNo;
	}
	public String getSignDateStart() {
		return signDateStart;
	}
	public void setSignDateStart(String signDateStart) {
		this.signDateStart = signDateStart;
	}
	public String getSignDateEnd() {
		return signDateEnd;
	}
	public void setSignDateEnd(String signDateEnd) {
		this.signDateEnd = signDateEnd;
	}
	public List<String> getProjStuList() {
		return projStuList;
	}
	public void setProjStuList(List<String> projStuList) {
		this.projStuList = projStuList;
	}
	public String getProjStatusIdStart() {
		return projStatusIdStart;
	}
	public void setProjStatusIdStart(String projStatusIdStart) {
		this.projStatusIdStart = projStatusIdStart;
	}
	public String getProjStatusIdEnd() {
		return projStatusIdEnd;
	}
	public void setProjStatusIdEnd(String projStatusIdEnd) {
		this.projStatusIdEnd = projStatusIdEnd;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
