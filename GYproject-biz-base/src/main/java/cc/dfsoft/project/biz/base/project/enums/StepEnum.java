
package cc.dfsoft.project.biz.base.project.enums;

public enum StepEnum {
	
	PLAN_PROJECT_ACCEPT("1100","计划立项",0),

	STATION_ACCEPT("7001","场站立项",0),
	AUDIT_PROCEDURES("7102","建审手续",5),
	STATION_CONSTRUCTION("7203","工程施工",5),
	GETSTATION_SETTLEMENT("7304","工程结算",5),

	MARKET_DISPATCH("11000","市场派工",0),

	PROJECT_ACCEPT("1101","受理申请",0),
	PROJECT_ACCEPT_AUDIT("110101","受理审核",5),
	DESIGN_DISPATCH("1102","勘察派工",5),
	SURVEY_RESULT_REGISTER("1103","现场踏勘",5),
	CONNECT_GAS_AUDIT("1104","踏勘审核",5),

	RAISEMONEY_APPLY("11041", "提资申请",5),
	RAISEMONEY_AUDIT("11042", "提资审核",5),
	CUST_RESPONSE("11043", "回复用户",5),
	
	WHITE_MAP("11044","白图登记",5),
	WHITE_MAP_AUDIT("11045","白图审核",5),
	
	DATA_ACQUISITION("1105","资料收集",5),
	PROJECT_CONFIRM("1106","立项确认",5),

	DESIGN_DISPATCH_WORKERS("1200","设计派工",5),
	DESIGN_DRAWING("1201","设计出图",5),
	DRAWING_SIGN_AUDIT("1202","图纸审核",5),
	OFFICIAL_DRAWING("1203","图纸签收",5),
	BUDGET_CONFIRM("12031","预算方式确认",5),
	
	BUDGET_DISPATCH("1301","预算派工",5),
	BUDGET_RESULT_REGISTER("1302","预算记录",5),
	BUDGET_AUDIT("1303","预算审核",5),
	BUDGET_GOV_AUDIT_COST("1304","预算审定价",5),  //政府预算评审
	PROJECT_COST_DISPATCH("13041","造价派工",5),
	PROJECT_COST("1401","工程造价",5),
	PROJECT_COST_AUDIT("14011","工程造价审批",5),
	TO_CONTRACT_REVIEW("14012","合同评审",5),
	CONSTRUCT_CONTRACT("1402","安装合同",5),
	CONTRACT_AUDIT("1403","合同审核",5),
	
	CONSTRUCTION_PROC("15011","报审手续登记",5),
	CONFIRM_CHARGE("15012","收款确认",5),
	PROJECT_PLAN("1501","工程计划",5),
	PROJECT_PLAN_AUDIT("1502","计划审核",5),

	CONSTRUCTION_DISPATCH("110507","施工派遣",5),
	SUPERVISION_DISPATCH("110508","监理派遣",5),

	PROJECT_INST_TASKS("1503","安装任务",5),
	AUDIT_INST_TASKS("150201","安装任务审核",5),

	QUALITIES_DECLARATION("1601","施工预算",5),
	QUALITIES_DISPATCH("1602","预算审核派工",5),
	QUALITIES_JUDGEMENT_FIRST("160201","预算初审",5),
	QUALITIES_JUDGEMENT("1603","施工预算审核",5),
	SUB_CONTRACT("1604","施工合同",5),
	SUB_CONTRACT_AUDIT("1605","施工合同审核",5),

	TECHNOLOGYTELL("1699", "会审交底",5),
	CONSTRUCTIONORGANIZATION("1700", "施工组织",5),
	CONSTRUCTION("1701", "开工报告",5),
	DURING_CONSTRUCTION("1702", "试压记录",5),
	
	SELF_CHECK("1801","工程自检",5),
	COMPLETED_REPORT("120502","竣工报告",5),  // 应更改成竣工报告菜单ID,否则操作记录stepID同步骤下有两个相同的stepID
	PRE_INSPECTION("1802","预验收",5),

	COMPLETION_DATA_APPLY("1803","资料验收申请",5),
	COMPLETION_DATA_AUDIT("1804","资料审核",5),
	UNION_PRE_INSPECTION_APPLY("18041","联合验收申请",5),
	UNION_PRE_INSPECTION_AUDIT("18042","联合验收审核",5),
	UNION_PRE_INSPECTION("1805","联合验收",5),
	ONE_STOP_ACCEPTANCE("1806","一站式验收",5),
	
	MONITORING_REPORT("19011","监检报告",5),
	SETTLEMENT_REPORT("1901","结算报审",5),
	SETTLEMENT_REPORT_START("1902","结算初审",5),
	AUDIT_DONE_DISPATCH("1903","终审派遣",2),
	REPORT_DONE_CONFIRM("1904","终审确认",2),
	SETTLEMENT_JONINTLY_SIGN("1999","结算汇签",5),
	SETTLEMENT_GOV_AUDIT_COST("19041","结算审定价",5),
	
	TURN_FIXED_APPLY("1905","转固申请",5),
	TURN_FIXED_AUDIT("1906","转固审核",5),

	REGISTRATION_OF_RECEIPTS("110903","收款登记",5),
	
	/*APPLY_COMPLETE_DRAWING("1905","电子图审核申请",2),
	AUDIT_COMPLETE_DRAWING("1906","电子图审核",2),*/
	
	CONNECT_CONFIRM("1907","资料归档",5),


	DATATABLE_INFO("6868","数据表信息修改",5),
	/*GAS_CONFIRM("18041","通气申请",5),
	REPORT_CONFIRM("19011","报审确认",2),
	AUDIT_START_DISPATCH("19012","初审派遣",2),
	REPORT_START_CONFIRM("19021","初审确认",2),
	COMPLETION_TRANSFER("1804","资料发放",5),
	DATA_FEEDBACK("1805","资料反馈",5),
	*/
	ALREADY_COMPLETED("3001","已竣工",5),
	CONTRACT_END("4001","终止",5);




	private final String value;
    
    private final String message;
    
    private final int stepDay;
    
    StepEnum(String value, String message,int stepDay){
        this.value = value;
        this.message = message;
        this.stepDay=stepDay;
    }

	public static String getNameByCode(String code){
		for(StepEnum e: StepEnum.values()) {
			if(e.getValue().equals(code)) {
				return e.getMessage();
			}
		}
		return null;
	}


    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public int getStepDay() {
		return stepDay;
	}

	public static StepEnum valueof(String value) {
    	for(StepEnum e: StepEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
