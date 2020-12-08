package cc.dfsoft.uexpress.common.enums;

/**
 * 工作流
 * @author pengtt
 */
public enum WorkFlowActionEnum {
	
	PROJECT_ACCEPT("1101","受理申请","1000","受理申请"),
	PROJECT_ACCEPT_AUDIT("110101","受理审核","100101","待受理审核"),
	DESIGN_DISPATCH("1102","勘察派工","1001","待勘察派工"),
	SURVEY_RESULT_REGISTER("1103","现场踏勘","1002","待现场踏勘"),
	PROGRAMME_AUDIT("1104","踏勘审核","1003","待踏勘审核"),


	RAISEMONEY_APPLY("11041","提资申请","10031","待提资申请"),
	RAISEMONEY_AUDIT("11042","提资审核","10032","待提资审核"),
	CUST_RESPONSE("11043","用户回复","10033","待用户回复"),

	DATA_ACQUISITION("1105","资料收集","1004","待资料收集"),

	DESIGN_DISPATCH_WORKERS("1200","设计派工","10041","待设计派工"),
	DESIGN_DRAWING("1201","设计出图","1005","待出草图"),
	DRAWING_SIGN_AUDIT("1202","图纸审核","1006","待图纸审核"),
	OFFICIAL_DRAWING("1203","图纸签收","1007","待图纸签收"),
	BUDGET_CONFIRM("12031","预算方式确定","10071","待预算方式确定"),
	
	WHITE_MAP("11044","白图登记","10034","待白图登记"),
	WHITE_MAP_AUDIT("11045","白图审核","10035","待白图审核"),
	
	BUDGET_DISPATCH("1301","预算派工","1008", "待预算派工"),
	BUDGET_RESULT_REGISTER("1302","预算记录","1009","待预算记录"),
	BUDGET_AUDIT("1303","预算审核","1010", "待预算审核"),
	BUDGET_GOV_AUDIT_COST("1304","预算审定价","10101", "待预算审定价登记"),
	
	PROJECT_COST_DISPATCH("13041","造价派工","10102","待造价派工"),
	PROJECT_COST("1401","工程造价","1011","待确定造价"),
	PROJECT_COST_AUDIT("14011","工程造价审核","10111","待工程造价审核"),
	PROJECT_CONFIRM("1106","立项确认","1012","待立项确认"),
	TO_CONTRACT_REVIEW("14012","合同评审","10121","合同评审"),
	CONSTRUCT_CONTRACT("1402","安装合同","1013","待签安装合同"),
	CONTRACT_AUDIT("1403","安装合同审核","1014","待审安装合同"),

	CONSTRUCTION_PROC("15011","报审手续","10151","待报审手续登记"),
	CONFIRM_CHARGE("15012","收款确认","10152","待收款确认"),
	PROJECT_PLAN("1501","工程计划","1015","待编计划"),
	PROJECT_PLAN_AUDIT("1502","计划审核","1016","待审计划"),
	PROJECT_INST_TASKS("1503","安装任务","10161","待安装任务"),
	
	INST_STASK_AUDIT("150201","安装任务审核","101602","待安装任务审核"),
	QUALITIES_DECLARATION("1601","施工预算","1017","待施工预算"),
	QUALITIES_DISPATCH("1602","预算审核派工","1018","待预算审核"),
	QUALITIES_DISPATCH_FIRST_AUDIT("160201","施工预算初审","101801","待预算初审"),
	QUALITIES_JUDGEMENT("1603","工程量审定","1019","待审工程量"),
	SUB_CONTRACT("1604","分包合同","1020","待签分包"),
	SUB_CONTRACT_AUDIT("1605","施工合同审核","10201","待审施工合同"),
	
	CONSTRUCTION("1701", "开工报告","1021","待施工"),
	DURING_CONSTRUCTION("1702", "试压完成","1022","施工中"),
	
	
	SELF_CHECK("1801","工程自检","1023","待自检"),
	PRE_INSPECTION("1802","预验收","1024","待预验收"),
	COMPLETION_DATA_APPLY("1803","资料验收申请","1025","待资料验收申请"),
	COMPLETION_DATA_AUDIT("1804","资料审核","1026","待资料审核"),
	UNION_PRE_INSPECTION_APPLY("18041","联合验收申请","10261","待联合验收申请"),
	UNION_PRE_INSPECTION_AUDIT("18042","联合验收审核","10262","待联合验收审核"),
	UNION_PRE_INSPECTION("1805","联合验收","1027","待联合验收"),
	ONE_STOP_ACCEPTANCE("1806","一站式验收","10271","待一站式验收"),
	
	MONITORING_REPORT("19011","监检报告","10281","待监检报告登记"),
	SETTLEMENT_REPORT("1901","结算报审","1028","待结算报审"),
	SETTLEMENT_REPORT_START("1902","结算初审","1029","待结算初审"),
	AUDIT_DONE_DISPATCH("1903","终审派遣","1030","待终审派遣"),
	REPORT_DONE_CONFIRM("1904","终审确认","1031","待终审确认"),
	SETTLEMENT_GOV_AUDIT_COST("19041","结算审定价","1032", "待结算审定价登记"),
	TURN_FIXED_APPLY("1905","转固申请","1033","待转固申请"),
	TURN_FIXED_AUDIT("1906","转固审核","1034","待转固审核"),
	/*APPLY_COMPLETE_DRAWING("1905","电子图审核申请","1033","待电子图审核申请"),
	AUDIT_COMPLETE_DRAWING("1906","电子图审核","1034","待电子图审核"),*/
	CONNECT_CONFIRM("1907","资料审核","1035","待资料审核"),
	
	AERATE_APPLY("1","通气申请","10221","待通气申请"),
	REPORT_CONFIRM("1","报审确认","10231","待报审确认"),
	AUDIT_START_DISPATCH("1","初审派遣","10232","待初审派遣"),
	REPORT_START_CONFIRM("1","初审确认","10241","待初审确认"),
	SETTLEMENT_REPORT_DONE("1","结算终审","1025","待结算终审"),
	COMPLETION_TRANSFER("1","资料发放","1026","待资料发放"),
	DATA_FEEDBACK("1","资料反馈","1027","待资料反馈"),
	
	ALREADY_COMPLETED ("3001","已竣工","1036","已竣工"),
	CONTRACT_END("4001","终止","2001","终止"),
	ALREADY_HANDED_OVER("2001","已移交","4001","已移交"),
	//场站
	STATION_ACCEPT("7001","场站立项","",""),
	AUDIT_PROCEDURES("7002","建审手续","7701","待建审手续"),
	STATION_CONSTRUCTION("7003","工程施工","7702","待工程施工"),
	GETSTATION_SETTLEMENT("7004","工程结算","7703","待工程结算");

	private final String actionCode;

	private final String actionDes;
	
	private final String statusCode;
	
	private final String statusDes;

	WorkFlowActionEnum(String actionCode, String actionDes,String statusCode,String statusDes) {
		
		this.actionCode = actionCode;
		this.actionDes = actionDes;
		this.statusCode = statusCode;
		this.statusDes = statusDes;
		
	}

	public String getActionCode() {
		return actionCode;
	}

	public String getActionDes() {
		return actionDes;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusDes() {
		return statusDes;
	}




	/**通过操作码获取枚举对象*/
	public static WorkFlowActionEnum byActionCode(String actionCode) {
		for (WorkFlowActionEnum e : WorkFlowActionEnum.values()) {
			if (e.getActionCode().equals(actionCode)) {
				return e;
			}
		}
		return null;
	}
	
	/**通过状态码获取枚举对象*/
	public static WorkFlowActionEnum byStatusCode(String statusCode) {
		for (WorkFlowActionEnum e : WorkFlowActionEnum.values()) {
			if (e.getStatusCode().equals(statusCode)) {
				return e;
			}
		}
		return null;
	}



	/**通过步骤码获取状态码*/
	public static String getStatusCodeByStepCode(String stepCode) {
		for (WorkFlowActionEnum e : WorkFlowActionEnum.values()) {
			if (stepCode.equals(e.getActionCode())) {
				return e.getStatusCode();
			}
		}
		return null;
	}


	/**通过状态码获取步骤码*/
	public static String getStepCodeByStatusCode(String statusCode) {
		for (WorkFlowActionEnum e : WorkFlowActionEnum.values()) {
			if (statusCode.equals(e.getStatusCode())) {
				return e.getActionCode();
			}
		}
		return null;
	}


}
