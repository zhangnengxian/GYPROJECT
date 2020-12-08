package cc.dfsoft.project.biz.base.design.dto;

/**
 * 更新设计派遣页面
 * @author Administrator
 *
 */
public class DesignDispatchDto{
	
	private String surveyer; 			//勘察人
	private String duName;	 			//设计院
	private String projId;	 			//工程ID
	private String projNo;	 			//工程编号
	private String unitName; 			//单设计院名称
	private String unitId;   			//设计院id
	private String depositGet;  		//是否收取设计定金
	
	private String designStation;		//设计所
	private String designStationId; 	//设计所ID
	private String designStationDirector;//负责人
	private String depositGetRemark;	//未收取设计定金说明
	private String surveyerId;
	private String designerId;//设计员id
	private String designer; //设计人员
	private boolean isFlag; //是否走流程
	
	private String projectRemark;		//受理备注
	
	private String businessOrderId;		//业务单ID
	private String menuId;				//菜单ID


	private String market;				//市场营销员
	private String marketId;			//市场营销员id


	public String getDesignStation() {
		return designStation;
	}
	public void setDesignStation(String designStation) {
		this.designStation = designStation;
	}
	public String getDesignStationId() {
		return designStationId;
	}
	public void setDesignStationId(String designStationId) {
		this.designStationId = designStationId;
	}
	public String getDesignStationDirector() {
		return designStationDirector;
	}
	public void setDesignStationDirector(String designStationDirector) {
		this.designStationDirector = designStationDirector;
	}
	public String getDepositGetRemark() {
		return depositGetRemark;
	}
	public void setDepositGetRemark(String depositGetRemark) {
		this.depositGetRemark = depositGetRemark;
	}
	public String getDuName() {
		return duName;
	}
	public void setDuName(String duName) {
		this.duName = duName;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getSurveyer() {
		return surveyer;
	}
	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getDepositGet() {
		return depositGet;
	}
	public void setDepositGet(String depositGet) {
		this.depositGet = depositGet;
	}
	public String getSurveyerId() {
		return surveyerId;
	}
	public void setSurveyerId(String surveyerId) {
		this.surveyerId = surveyerId;
	}
	public String getDesignerId() {
		return designerId;
	}
	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}
	public String getProjectRemark() {
		return projectRemark;
	}
	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}
	public String getBusinessOrderId() {
		return businessOrderId;
	}
	public void setBusinessOrderId(String businessOrderId) {
		this.businessOrderId = businessOrderId;
	}
	public String getDesigner() {
		return designer;
	}
	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public boolean getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}
}
