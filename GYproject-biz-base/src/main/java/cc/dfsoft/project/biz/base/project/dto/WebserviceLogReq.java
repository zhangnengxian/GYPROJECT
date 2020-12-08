package cc.dfsoft.project.biz.base.project.dto;

import java.util.Date;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 接口日志查询辅助类
 * @author liaoyq
 * @createTime 2018年8月7日
 */
public class WebserviceLogReq extends PageSortReq{

	private String projId;
	private String projNo;
	private String resultType;	//返回值类型
	private Date logTime;		//接口调用时间
	private String operateType;	//操作类型
	private String serviceType; //接口类型
	
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
}
