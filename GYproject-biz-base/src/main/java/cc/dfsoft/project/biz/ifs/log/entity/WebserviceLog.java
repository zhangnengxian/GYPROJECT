package cc.dfsoft.project.biz.ifs.log.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;

/**
 * 接口调用日志记录实体类
 * @author liaoyq
 *
 */
@Entity
@Table(name="WEBSERVICE_LOG")
public class WebserviceLog {
	private String logId;		//主键ID
	private String logParams;		//调用参数
	private String resultType;	//返回值类型
	private String resultMsg;	//返回信息
	private Date logTime;		//接口调用时间
	private String operateType;	//操作类型
	private String serviceType; //接口类型
	private String serviceTypeDes; //接口类型-描述
	private String projId;		//工程ID
	private String projNo;		//工程编号
	private String servicePath;		//接口访问路径
	
	public WebserviceLog() {
		super();
	}
	
	@Id
	@Column(name="LOG_ID",unique = true)
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	@Column(name="LOG_PARAMS")
	public String getLogParams() {
		return logParams;
	}
	public void setLogParams(String logParams) {
		this.logParams = logParams;
	}
	
	@Column(name="RESULT_TYPE")
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	@Column(name="RESULT_MSG")
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LOG_TIME")
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
	@Column(name="OPERATE_TYPE")
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name="SERVICE_TYPE")
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="PROJ_NO")
	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Transient
	public String getServiceTypeDes() {
		for(WebServiceTypeEnum e: WebServiceTypeEnum.values()) {
	   		if(e.getValue().equals(this.serviceType)) {
	   			return e.getMessage();
	   		}
	   	}
		return serviceTypeDes;
	}

	public void setServiceTypeDes(String serviceTypeDes) {
		this.serviceTypeDes = serviceTypeDes;
	}

	@Column(name="SERVICE_PATH")
	public String getServicePath() {
		return servicePath;
	}

	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}
	
	
}
