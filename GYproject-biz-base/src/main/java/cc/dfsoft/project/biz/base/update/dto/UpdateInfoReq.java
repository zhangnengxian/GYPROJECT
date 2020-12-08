package cc.dfsoft.project.biz.base.update.dto;

import java.util.Date;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 
 * @author dn
 * 系统更新内容
 */
public class UpdateInfoReq  extends PageSortReq {

	private String updateId;		//更新Id

	private String updateNo;		//更新号
	
	private String updateTime;	//更新时间
	
	private String updateTimeStart;	//更新开始时间
	
	private String updateTimeEnd;	//更新结束时间
	
	private String updateContent;		//更新内容
	
	private String updateNumber;		//更新次数
	private String corpId;				//分公司id
	public String getUpdateId() {
		return updateId;
	}
	
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	public String getUpdateNo() {
		return updateNo;
	}
	
	public void setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateContent() {
		return updateContent;
	}
	
	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public String getUpdateNumber() {
		return updateNumber;
	}

	public void setUpdateNumber(String updateNumber) {
		this.updateNumber = updateNumber;
	}

	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
}