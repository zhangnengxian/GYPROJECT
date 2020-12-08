package cc.dfsoft.project.biz.base.baseinfo.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 
 * @author Yuanyx
 *
 */
public class CorrelationReq extends PageSortReq{
	
	private String correlateInfoDes;	//相关信息描述
	private String corType;				//类型
	private String correlateInfoId;		//关系id
	private String acceptType;			//立项方式
	private String scaleType;//
	
	private String acceptScaleType;    //立项时查询-小规模 大规模
	private String acceptCorrelatedInfoId;//只用于-立项时设置部门id
	private String correlatedInfoId;
	
	private String corpId;			//分公司id
	
	public String getCorrelateInfoDes() {
		return correlateInfoDes;
	}

	public void setCorrelateInfoDes(String correlateInfoDes) {
		this.correlateInfoDes = correlateInfoDes;
	}

	public String getCorType() {
		return corType;
	}

	public void setCorType(String corType) {
		this.corType = corType;
	}

	public String getCorrelateInfoId() {
		return correlateInfoId;
	}

	public void setCorrelateInfoId(String correlateInfoId) {
		this.correlateInfoId = correlateInfoId;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}

	public String getAcceptScaleType() {
		return acceptScaleType;
	}

	public void setAcceptScaleType(String acceptScaleType) {
		this.acceptScaleType = acceptScaleType;
	}

	public String getAcceptCorrelatedInfoId() {
		return acceptCorrelatedInfoId;
	}

	public void setAcceptCorrelatedInfoId(String acceptCorrelatedInfoId) {
		this.acceptCorrelatedInfoId = acceptCorrelatedInfoId;
	}

	public String getCorrelatedInfoId() {
		return correlatedInfoId;
	}

	public void setCorrelatedInfoId(String correlatedInfoId) {
		this.correlatedInfoId = correlatedInfoId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	

}
