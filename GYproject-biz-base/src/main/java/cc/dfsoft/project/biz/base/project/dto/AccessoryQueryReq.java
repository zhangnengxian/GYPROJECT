package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.util.List;

/**
 * 
 * @author jingjing
 *
 */
public class AccessoryQueryReq extends PageSortReq{
	
	private String projNo;				//工程编号
	private String projId;				//工程id
	private String projLtypeId;			//工程大类
	private String projStatusDes;		//工程状态
	
	private String caiId;				//资料类型id
	private String accessoryName;		//资料名称
	private String stepId;				//步骤
	private String busRecordId;         //业务记录id
	private String sourceType;      	//附件来源 0 采集 1 拍照
	
	private String encryption;			//是否加密 1是 0否
	
	private String dataType;			//资料类型
	
	private String projTypeId;			//工程类型id
	private List<String> caiIdList;	 	//id集合
	
	private List<String> staffIdList;	//员工id集合

	private String step;				//步骤id
	
	private String corpId;				//分公司id
	private String stepIds;		//步骤ID集合
	
	private String sourceTypes;	//附件来源 0 采集 1 拍照2-附件,以逗号隔开

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjLtypeId() {
		return projLtypeId;
	}
	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	public String getProjStatusDes() {
		return projStatusDes;
	}
	public void setProjStatusDes(String projStatusDes) {
		this.projStatusDes = projStatusDes;
	}
	public String getCaiId() {
		return caiId;
	}
	public void setCaiId(String caiId) {
		this.caiId = caiId;
	}
	public String getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getBusRecordId() {
		return busRecordId;
	}
	public void setBusRecordId(String busRecordId) {
		this.busRecordId = busRecordId;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getEncryption() {
		return encryption;
	}
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getProjTypeId() {
		return projTypeId;
	}
	public void setProjTypeId(String projTypeId) {
		this.projTypeId = projTypeId;
	}
	public List<String> getCaiIdList() {
		return caiIdList;
	}
	public void setCaiIdList(List<String> caiIdList) {
		this.caiIdList = caiIdList;
	}
	public List<String> getStaffIdList() {
		return staffIdList;
	}
	public void setStaffIdList(List<String> staffIdList) {
		this.staffIdList = staffIdList;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getStepIds() {
		return stepIds;
	}

	public void setStepIds(String stepIds) {
		this.stepIds = stepIds;
	}

	public String getSourceTypes() {
		return sourceTypes;
	}

	public void setSourceTypes(String sourceTypes) {
		this.sourceTypes = sourceTypes;
	}
	
}
