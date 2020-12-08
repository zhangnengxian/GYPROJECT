package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;

/**
 * 
 * 描述:无损探、业务沟通伤辅助类
 * @author liaoyq
 * @createTime 2017年9月27日
 */
public class NdeRecordBcDto {
	private String bcId;
	private BusinessCommunication bCommunication;
	private NdeRecord ndeRecord;
	public String getBcId() {
		return bcId;
	}
	public void setBcId(String bcId) {
		this.bcId = bcId;
	}
	public BusinessCommunication getbCommunication() {
		return bCommunication;
	}
	public void setbCommunication(BusinessCommunication bCommunication) {
		this.bCommunication = bCommunication;
	}
	public NdeRecord getNdeRecord() {
		return ndeRecord;
	}
	public void setNdeRecord(NdeRecord ndeRecord) {
		this.ndeRecord = ndeRecord;
	}
	
	
}
