package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.math.BigDecimal;
import java.util.List;

/**
 * 工程查询
 * @author pengtt
 * @createTime 2016-06-21
 *
 */
public class ProjectQueryReq extends PageSortReq{

	private String planId;				//计划Id
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projLtypeId;			//工程大类ID
	private String projAddr;			//工程地点
	private String area;				//区域
	private String projStatusId;		//状态
	private String projScaleDes;		//工程规模
	private String acceptDateStart;		//受理日期开始日期
	private String acceptDateEnd;		//受理日期结束日期
	private String duCompleteDateStart;	//设计完成日期开始日期
	private String duCompleteDateEnd;	//设计完成日期结束日期
	private String surveyDateStart;		//勘察日期开始日期
	private String surveyDateEnd;		//勘察日期结束日期
	private String surveyer;			//勘察人
	private String dispatchSurveyer;    //派遣勘察人
	private String costMember;			//造价员
	private String budgeter;			//预算员
	private String budgeterId;			//预算员ID
	private String settlementer;		//结算人
	private String budgetDateStart;		 //预算日期开始日期
	private String budgetDateEnd;		 //预算日期结束日期
	private String contractSignDateStart;//合同签订开始日期
	private String contractSignDateEnd;  //合同签订结束日期
	private String builder;				 //现场代表
	private String suJgj;				 //现场监理
	
	private String plannedStartDateStart;//计划开工日期开始-合同签订
	private String plannedStartDateEnd;  //计划开工日期截止-合同签订
	private String plannedEndDateStart;	 //计划竣工日期-合同签订
	private String plannedEndDateEnd;	 //计划竣工日期-合同签订
	
	
	
	private String acceptanceDateStart;	 //验收开始日期
	private String acceptanceDateEnd;	 //验收结束日期
	private String settlementDateStart;	 //结算开始日期
	private String settlementDateEnd;	 //结算结束日期
	private String affirmCostDateStart;  //造价确认开始日期
	private String affirmCostDateEnd; 	 //造价确认截止日期
	private Integer timeLimit;	         //时间限制
	private String  stepId;	             //步骤
	private String projStatus;			 //工程状态-----立项申请查询
	private List<String> projStuList;	 //工程状态集合
	private Boolean backInform;			 //退单通知
	
	/********* 以下两个属性用于工程概述查询  ************/
	private String size; 				//每页条数
	private String currentPage; 		//当前页
	
	private String startDateStart; 		 //开工开始日期
	private String startDateEnd; 	     //开工截止日期
	private String completeDateStart; 	 //竣工开始日期
	private String completeDateEnd; 	 //竣工截止日期
	private String finishedDateStart;    //结束开始日期
	private String finishedDateEnd;      //结束开始日期
	private String subContractDateStart; //分包合同签定开始日期
	private String subContractDateEnd;   //分包合同签定开始日期
	
	private String conNo;				//合同编号
	
	private String sideBarID; 	        //当前选中的sidebarID
	private String cuName;              //分包单位
	
	private String cuId;				//分包单位id
	
	private String managementOffice;	//施工处
	private String managementOfficeId;	//施工处Id
	
	private String custName;            //客户名称
	private String designer; 			//设计员
	private String operator;            //合同员
	private String paNo;				//受理单号
	private String designStationId;		//设计所
	
	private String isBack;				//是否退单
	private String backReason;			//退单原因
	private String projId;				//工程id
	
	private String arFlag;				//收付标志
	private String arStatus;			//已收、未收
	private String arPayStatus;			//已付、未付
	private String collectionType;		//收付类型
	private String contractType;	    //是否智能表
	
	private String settlementerId;		//结算员id
	private String costMangeerId;		//造价经理id
	
	private String isModify;			//是否更正
	
	private String goodsCompleteStatus;//发货完成标记
	private String feedbackState;		//反馈类型
	
	private String acceptType;			//立项方式 1 受理申请 2 计划立项
	
	
	private BigDecimal designFee;			//设计费
	private String remark;					//备注
	
	private String isIntelligentMeter;		//是否是智能表工程
	private String homePageRole;			//首页查询权限
	
	private String flag;					//推送状态

	private String projectType;				//工程类型
	private String contributionMode;		//出资方式
	
	
	private String noticeType;				//通知类型
	private String budgeterAudit;			//分包预算审核人

	private String deptId;				//业务部门
	private String signStatus;			//汇签状态
	
	private String finishDateStart;		//结算汇签完成开始时间
	private String finishDateEnd;		//结算汇签完成结束时间
	private String startStatusId;		//开始状态
	private String endStatusId;			//结束状态
	private String auditState;			//电子申请图审核状态
	
	private String menuId;				//菜单id
	private String corpId;				//分公司id
	private String corpName;				//分公司名称
	private Boolean redFlag; //是否标红（特殊工程）
	private String custContact;
	private String custPhone;
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(String contributionMode) {
		this.contributionMode = contributionMode;
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
	public String getProjLtypeId() {
		return projLtypeId;
	}
	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getProjScaleDes() {
		return projScaleDes;
	}
	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	public String getAcceptDateStart() {
		return acceptDateStart;
	}
	public void setAcceptDateStart(String acceptDateStart) {
		this.acceptDateStart = acceptDateStart;
	}
	public String getAcceptDateEnd() {
		return acceptDateEnd;
	}
	public void setAcceptDateEnd(String acceptDateEnd) {
		this.acceptDateEnd = acceptDateEnd;
	}
	public String getDuCompleteDateStart() {
		return duCompleteDateStart;
	}
	public void setDuCompleteDateStart(String duCompleteDateStart) {
		this.duCompleteDateStart = duCompleteDateStart;
	}
	public String getDuCompleteDateEnd() {
		return duCompleteDateEnd;
	}
	public void setDuCompleteDateEnd(String duCompleteDateEnd) {
		this.duCompleteDateEnd = duCompleteDateEnd;
	}
	public String getSurveyDateStart() {
		return surveyDateStart;
	}
	public void setSurveyDateStart(String surveyDateStart) {
		this.surveyDateStart = surveyDateStart;
	}
	public String getSurveyDateEnd() {
		return surveyDateEnd;
	}
	public void setSurveyDateEnd(String surveyDateEnd) {
		this.surveyDateEnd = surveyDateEnd;
	}
	public String getSurveyer() {
		return surveyer;
	}
	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}
	public String getCostMember() {
		return costMember;
	}
	public void setCostMember(String costMember) {
		this.costMember = costMember;
	}
	public String getBudgeter() {
		return budgeter;
	}
	public void setBudgeter(String budgeter) {
		this.budgeter = budgeter;
	}
	public String getSettlementer() {
		return settlementer;
	}
	public void setSettlementer(String settlementer) {
		this.settlementer = settlementer;
	}
	public String getBudgetDateStart() {
		return budgetDateStart;
	}
	public void setBudgetDateStart(String budgetDateStart) {
		this.budgetDateStart = budgetDateStart;
	}
	public String getBudgetDateEnd() {
		return budgetDateEnd;
	}
	public void setBudgetDateEnd(String budgetDateEnd) {
		this.budgetDateEnd = budgetDateEnd;
	}
	public String getContractSignDateStart() {
		return contractSignDateStart;
	}
	public void setContractSignDateStart(String contractSignDateStart) {
		this.contractSignDateStart = contractSignDateStart;
	}
	public String getContractSignDateEnd() {
		return contractSignDateEnd;
	}
	public void setContractSignDateEnd(String contractSignDateEnd) {
		this.contractSignDateEnd = contractSignDateEnd;
	}
	public String getAcceptanceDateStart() {
		return acceptanceDateStart;
	}
	public void setAcceptanceDateStart(String acceptanceDateStart) {
		this.acceptanceDateStart = acceptanceDateStart;
	}
	public String getAcceptanceDateEnd() {
		return acceptanceDateEnd;
	}
	public void setAcceptanceDateEnd(String acceptanceDateEnd) {
		this.acceptanceDateEnd = acceptanceDateEnd;
	}
	public String getSettlementDateStart() {
		return settlementDateStart;
	}
	public void setSettlementDateStart(String settlementDateStart) {
		this.settlementDateStart = settlementDateStart;
	}
	public String getSettlementDateEnd() {
		return settlementDateEnd;
	}
	public void setSettlementDateEnd(String settlementDateEnd) {
		this.settlementDateEnd = settlementDateEnd;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getStartDateStart() {
		return startDateStart;
	}
	public void setStartDateStart(String startDateStart) {
		this.startDateStart = startDateStart;
	}
	public String getStartDateEnd() {
		return startDateEnd;
	}
	public void setStartDateEnd(String startDateEnd) {
		this.startDateEnd = startDateEnd;
	}
	public String getCompleteDateStart() {
		return completeDateStart;
	}
	public void setCompleteDateStart(String completeDateStart) {
		this.completeDateStart = completeDateStart;
	}
	public String getCompleteDateEnd() {
		return completeDateEnd;
	}
	public void setCompleteDateEnd(String completeDateEnd) {
		this.completeDateEnd = completeDateEnd;
	}
	public String getSideBarID() {
		return sideBarID;
	}
	public void setSideBarID(String sideBarID) {
		this.sideBarID = sideBarID;
	}
	public String getAffirmCostDateStart() {
		return affirmCostDateStart;
	}
	public void setAffirmCostDateStart(String affirmCostDateStart) {
		this.affirmCostDateStart = affirmCostDateStart;
	}
	public String getAffirmCostDateEnd() {
		return affirmCostDateEnd;
	}
	public void setAffirmCostDateEnd(String affirmCostDateEnd) {
		this.affirmCostDateEnd = affirmCostDateEnd;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getSubContractDateStart() {
		return subContractDateStart;
	}
	public void setSubContractDateStart(String subContractDateStart) {
		this.subContractDateStart = subContractDateStart;
	}
	public String getSubContractDateEnd() {
		return subContractDateEnd;
	}
	public void setSubContractDateEnd(String subContractDateEnd) {
		this.subContractDateEnd = subContractDateEnd;
	}
	public String getFinishedDateStart() {
		return finishedDateStart;
	}
	public void setFinishedDateStart(String finishedDateStart) {
		this.finishedDateStart = finishedDateStart;
	}
	public String getFinishedDateEnd() {
		return finishedDateEnd;
	}
	public void setFinishedDateEnd(String finishedDateEnd) {
		this.finishedDateEnd = finishedDateEnd;
	}
	public String getProjStatus() {
		return projStatus;
	}
	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}
	public List<String> getProjStuList() {
		return projStuList;
	}
	public void setProjStuList(List<String> projStuList) {
		this.projStuList = projStuList;
	}
	public Boolean getBackInform() {
		return backInform;
	}
	public void setBackInform(Boolean backInform) {
		this.backInform = backInform;
	}
	public String getDispatchSurveyer() {
		return dispatchSurveyer;
	}
	public void setDispatchSurveyer(String dispatchSurveyer) {
		this.dispatchSurveyer = dispatchSurveyer;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDesigner() {
		return designer;
	}
	public void setDesigner(String designer) {
		this.designer = designer;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getPaNo() {
		return paNo;
	}
	public void setPaNo(String paNo) {
		this.paNo = paNo;
	}
	public String getDesignStationId() {
		return designStationId;
	}
	public void setDesignStationId(String designStationId) {
		this.designStationId = designStationId;
	}
	public String getManagementOffice() {
		return managementOffice;
	}
	public void setManagementOffice(String managementOffice) {
		this.managementOffice = managementOffice;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getIsBack() {
		return isBack;
	}
	public void setIsback(String isBack) {
		this.isBack = isBack;
	}
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getArFlag() {
		return arFlag;
	}
	public void setArFlag(String arFlag) {
		this.arFlag = arFlag;
	}
	public String getArStatus() {
		return arStatus;
	}
	public void setArStatus(String arStatus) {
		this.arStatus = arStatus;
	}
	public String getArPayStatus() {
		return arPayStatus;
	}
	public void setArPayStatus(String arPayStatus) {
		this.arPayStatus = arPayStatus;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public String getSettlementerId() {
		return settlementerId;
	}
	public void setSettlementerId(String settlementerId) {
		this.settlementerId = settlementerId;
	}
	public String getCostMangeerId() {
		return costMangeerId;
	}
	public void setCostMangeerId(String costMangeerId) {
		this.costMangeerId = costMangeerId;
	}
	public String getPlannedStartDateStart() {
		return plannedStartDateStart;
	}
	public void setPlannedStartDateStart(String plannedStartDateStart) {
		this.plannedStartDateStart = plannedStartDateStart;
	}
	public String getPlannedStartDateEnd() {
		return plannedStartDateEnd;
	}
	public void setPlannedStartDateEnd(String plannedStartDateEnd) {
		this.plannedStartDateEnd = plannedStartDateEnd;
	}
	public String getPlannedEndDateStart() {
		return plannedEndDateStart;
	}
	public void setPlannedEndDateStart(String plannedEndDateStart) {
		this.plannedEndDateStart = plannedEndDateStart;
	}
	public String getPlannedEndDateEnd() {
		return plannedEndDateEnd;
	}
	public void setPlannedEndDateEnd(String plannedEndDateEnd) {
		this.plannedEndDateEnd = plannedEndDateEnd;
	}
	public String getManagementOfficeId() {
		return managementOfficeId;
	}
	public void setManagementOfficeId(String managementOfficeId) {
		this.managementOfficeId = managementOfficeId;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getIsModify() {
		return isModify;
	}
	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}
	public String getGoodsCompleteStatus() {
		return goodsCompleteStatus;
	}
	public void setGoodsCompleteStatus(String goodsCompleteStatus) {
		this.goodsCompleteStatus = goodsCompleteStatus;
	}
	public String getFeedbackState() {
		return feedbackState;
	}
	public void setFeedbackState(String feedbackState) {
		this.feedbackState = feedbackState;
	}
	public String getBudgeterId() {
		return budgeterId;
	}
	public void setBudgeterId(String budgeterId) {
		this.budgeterId = budgeterId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getAcceptType() {
		return acceptType;
	}
	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}
	public BigDecimal getDesignFee() {
		return designFee;
	}
	public void setDesignFee(BigDecimal designFee) {
		this.designFee = designFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsIntelligentMeter() {
		return isIntelligentMeter;
	}
	public void setIsIntelligentMeter(String isIntelligentMeter) {
		this.isIntelligentMeter = isIntelligentMeter;
	}
	public String getHomePageRole() {
		return homePageRole;
	}
	public void setHomePageRole(String homePageRole) {
		this.homePageRole = homePageRole;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getBuilder() {
		return builder;
	}
	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getBudgeterAudit() {
		return budgeterAudit;
	}

	public void setBudgeterAudit(String budgeterAudit) {
		this.budgeterAudit = budgeterAudit;
	}


	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getFinishDateStart() {
		return finishDateStart;
	}

	public void setFinishDateStart(String finishDateStart) {
		this.finishDateStart = finishDateStart;
	}

	public String getFinishDateEnd() {
		return finishDateEnd;
	}

	public void setFinishDateEnd(String finishDateEnd) {
		this.finishDateEnd = finishDateEnd;
	}

	public String getSuJgj() {
		return suJgj;
	}

	public void setSuJgj(String suJgj) {
		this.suJgj = suJgj;
	}

	public String getStartStatusId() {
		return startStatusId;
	}

	public void setStartStatusId(String startStatusId) {
		this.startStatusId = startStatusId;
	}

	public String getEndStatusId() {
		return endStatusId;
	}

	public void setEndStatusId(String endStatusId) {
		this.endStatusId = endStatusId;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	public Boolean getRedFlag() {
		return redFlag;
	}
	public void setRedFlag(Boolean redFlag) {
		this.redFlag = redFlag;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCustContact() {
		return custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

}
