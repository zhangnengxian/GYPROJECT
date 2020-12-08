package cc.dfsoft.project.biz.base.maintain.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

import javax.persistence.*;
import java.util.Date;

/**
 * 接口配置类
 * @author liaoyq
 * @createTime 2018年4月22日
 */

public class WebServiceSetReq extends PageSortReq {

	private String wsId;		//主键ID
	private String webServiceNo;	//调用编号
	private String webServiceName;	//接口名称
	private String webServiceFlag;	//开启状态
	private Date projAcceptDate;	//同步的工程立项时间设置
	private Date conSignDate;		//合同签订日期设置
	private String corpId;			//分公司ID
	private Integer isSync;//是否实时同步：1-是，0-不是

	public String getWsId() {
		return wsId;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

	public String getWebServiceNo() {
		return webServiceNo;
	}

	public void setWebServiceNo(String webServiceNo) {
		this.webServiceNo = webServiceNo;
	}

	public String getWebServiceName() {
		return webServiceName;
	}

	public void setWebServiceName(String webServiceName) {
		this.webServiceName = webServiceName;
	}

	public String getWebServiceFlag() {
		return webServiceFlag;
	}

	public void setWebServiceFlag(String webServiceFlag) {
		this.webServiceFlag = webServiceFlag;
	}

	public Date getProjAcceptDate() {
		return projAcceptDate;
	}

	public void setProjAcceptDate(Date projAcceptDate) {
		this.projAcceptDate = projAcceptDate;
	}

	public Date getConSignDate() {
		return conSignDate;
	}

	public void setConSignDate(Date conSignDate) {
		this.conSignDate = conSignDate;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public Integer getIsSync() {
		return isSync;
	}

	public void setIsSync(Integer isSync) {
		this.isSync = isSync;
	}
}
