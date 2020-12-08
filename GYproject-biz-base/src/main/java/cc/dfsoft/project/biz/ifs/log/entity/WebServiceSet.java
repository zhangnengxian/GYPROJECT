package cc.dfsoft.project.biz.ifs.log.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 接口配置类
 * @author liaoyq
 * @createTime 2018年4月22日
 */
@Entity
@Table(name="WEBSERVICE_SET")
public class WebServiceSet {

	private String wsId;		//主键ID
	private String webServiceNo;	//调用编号
	private String webServiceName;	//接口名称
	private String webServiceFlag;	//开启状态
	private Date projAcceptDate;	//同步的工程立项时间设置
	private Date conSignDate;		//合同签订日期设置
	private String corpId;			//分公司ID
	private Integer isSync;//是否实时同步：1-是，0-不是
	private String localUrl;	 //本地controller的url(手动数据同步用)
	private String serverUrl;	 //远程url

	
	public WebServiceSet() {
		super();
	}
	@Id
	@Column(name="WS_ID")
	public String getWsId() {
		return wsId;
	}
	public void setWsId(String wsId) {
		this.wsId = wsId;
	}
	@Column(name="WEBSERVICE_NO")
	public String getWebServiceNo() {
		return webServiceNo;
	}
	public void setWebServiceNo(String webServiceNo) {
		this.webServiceNo = webServiceNo;
	}
	@Column(name="WEBSERVICE_NAME")
	public String getWebServiceName() {
		return webServiceName;
	}
	public void setWebServiceName(String webServiceName) {
		this.webServiceName = webServiceName;
	}
	@Column(name="WEBSERVICE_FLAG")
	public String getWebServiceFlag() {
		return webServiceFlag;
	}
	public void setWebServiceFlag(String webServiceFlag) {
		this.webServiceFlag = webServiceFlag;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="PROJ_ACCEPT_DATE")
	public Date getProjAcceptDate() {
		return projAcceptDate;
	}
	public void setProjAcceptDate(Date projAcceptDate) {
		this.projAcceptDate = projAcceptDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="CON_SIGN_DATE")
	public Date getConSignDate() {
		return conSignDate;
	}
	public void setConSignDate(Date conSignDate) {
		this.conSignDate = conSignDate;
	}
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	@Column(name="IS_SYNC")
	public Integer getIsSync() {
		return isSync;
	}
	public void setIsSync(Integer isSync) {
		this.isSync = isSync;
	}

	@Column(name="LOCAL_URL")
	public String getLocalUrl() {
		return localUrl;
	}
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}
	@Column(name="SERVER_URL")
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
}
