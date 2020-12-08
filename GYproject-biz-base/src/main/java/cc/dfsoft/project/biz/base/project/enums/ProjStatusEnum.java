
package cc.dfsoft.project.biz.base.project.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public enum ProjStatusEnum {



	 TO_MARKET_DISPATCH("10000","市场派工"),
	 PROJECT_ACCEPT("1000","受理申请"),
	 PROJECT_ACCEPT_AUDIT("100101","待受理审核"),
	 TO_SURVEY("1001", "待勘察派工"),
	 /*TO_SURVEY_TWO("10011", "待勘察派工"),*/
	 TO_RESULT_REGISTRY("1002", "待现场踏勘"),
	 TO_APPROVAL("1003", "待踏勘审核"),

	 TO_RAISEMONEY_APPLY("10031", "待提资申请"),
	 TO_RAISEMONEY_AUDIT("10032", "待提资审核"),
	 TO_CUST_RESPONSE("10033", "待回复用户"),

	 TO_WHITE_MAP("10034","待白图登记"),
	 TO_WHITE_MAP_AUDIT("10035","待白图审核"),
	 
	 TO_DATA_ACQUISITION("1004","待资料收集"),
	 //TO_ENTRUST("1004", "待委托"),
	 TO_DESIGN_DISPATCH("10041", "待设计派工"),
	 TO_OUT_SKETCH("1005", "待设计出图"),
	 TO_DRAWING("1006", "待图纸审核"),
	 TO_FORMAL_DRAW("1007","待图纸签收"),
	 TO_BUDGET_CONFIRM("10071","待预算方式确认"),
	 
	 TO_BUDGET_DISPATCH("1008", "待预算派工"),
	 TO_BUDGET_RESULT_REGISTRATION("1009", "待预算记录"),
	 TO_BUDGET_AUDIT("1010", "待预算审核"),
	 TO_BUDGET_GOV_AUDIT_COST("10101", "待预算审定价登记"),
	 TO_COST_DISPATCH("10102","待造价派工"),
	 TO_DETERMINE_COST("1011", "待确定造价"),
	 TO_PROJECT_COST_AUDIT("10111", "待工程造价审核"),
	 TO_PROJECT_CONFIRM("1012","待立项确认"),
	 TO_CONTRACT_REVIEW("10121","合同评审"),
	 TO_SIGN_CONTRACT("1013", "待签安装合同"),
	 TO_AUDIT_CONTRACT("1014","待审安装合同"),
	 
	 TO_CONSTRUCTION_PROC("10151", "待报审手续登记"),
	TO_CONFIRM_CHARGE("10152", "待收款确认"),
	 TO_MAKE_PLAN("1015", "待编计划"),
	 TO_PLAN_AUDIT("1016","待审计划"),
	 TO_INST_TASKS("10161","待安装任务"),
	 
	 TO_AUDIT_INSTASKS("101602","待安装任务审核"),
	 
	 TO_DETERMINE_AMOUNT("1017", "待施工预算"),
	 TO_DETERMINE_DISPATCH("1018", "待预算审核派工"),
	TO_AUDIT_AMOUNT_FIRST("101801", "待施工预算初审"),
	TO_AUDIT_AMOUNT("1019", "待施工预算审核"),

	 TO_SIGNED_SUBCONTRACT("1020", "待签施工合同"),

	TO_AUDIT_SUBCONTRACT("10201", "待审施工合同"),
	 //TO_AUDIT_SUBCONTRACT("10171", "待审分包"),
	 TO_CONSTRUCTION("1021","待施工"),
	 DURING_CONSTRUCTION("1022","施工中"),
	 
	 TO_SELF_CHECK("1023","待自检"),
	 TO_PRE_INSPECTION("1024","待预验收"),
	 TO_COMPLETION_DATA_APPLY("1025","待资料验收申请"),
	 TO_COMPLETION_DATA_AUDIT("1026","待资料审核"),
	 TO_UNION_PRE_INSPECTION_APPLY("10261","待联合验收申请"),
	 TO_UNION_PRE_INSPECTION_AUDIT("10262","待联合验收审核"),
	 UNION_PRE_INSPECTION("1027","待联合验收"),
	 ONE_STOP_ACCEPTANCE("10271","待一站式验收"),
	 
	 TO_MONITORING_REPORT("10281","待监检报告登记"),
	 SETTLEMENT_REPORT("1028","待结算报审"),
	 SETTLEMENT_REPORT_START("1029","待结算初审"),
	 AUDIT_DONE_DISPATCH("1030","待终审派遣"),
	 REPORT_DONE_CONFIRM("1031","待终审确认"),
	 SETTLEMENT_GOV_AUDIT_COST("1032","待结算审定价登记"),
	 TURN_FIXED_APPLY("1033","待转固申请"),
	 TURN_FIXED_AUDIT("1034","待转固审核"),
	 /*APPLY_COMPLETE_DRAWING("1033","待电子图审核申请"),
	 AUDIT_COMPLETE_DRAWING("1034","待电子图审核"),*/
	 
	 CONNECT_CONFIRM("1035","待资料归档"),
	 ALREADY_COMPLETED ("1036","已竣工"),
	 
	 /*AERATE_APPLY("10221","待通气申请"),
	 REPORT_CONFIRM("10231","待报审确认"),
	 AUDIT_START_DISPATCH("10232","待初审派遣"),
	 REPORT_START_CONFIRM("10241","待初审确认"),
	 SETTLEMENT_REPORT_DONE("10251","待结算终审"),
	 DATA_FEEDBACK("10271","待资料反馈"),
	 COMPLETION_TRANSFER("10351","待资料发放"),
	 
	 ALREADY_FINISH ("2002","已结单"),*/

	 AUDIT_PROCEDURES ("7701","待建审手续"),
	 STATION_CONSTRUCTION ("7702","待工程施工"),
	 GETSTATION_SETTLEMENT ("7703","待工程结算"),

	 TERMINATION("2001", "终止"),
	 ALREADY_HANDED_OVER("4001","已移交");
    private final String value;
    
    private final String message;
    
    ProjStatusEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjStatusEnum valueof(String value) {
    	for(ProjStatusEnum e: ProjStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
     //startValue状态至endValue状态的工程状态(此方法使用前提：状态码大小必须是根据工作流程从小到大的顺序)
  	public static List<ProjStatusEnum> getThanValue(String StartValue,String EndValue) {
  		List<ProjStatusEnum> enums=new ArrayList<ProjStatusEnum>();
  		for (ProjStatusEnum e : ProjStatusEnum.values()) {
  			if(StringUtils.isNotBlank(StartValue) && StringUtils.isNotBlank(EndValue)){
  				if (Integer.parseInt(StartValue)<=Integer.parseInt(e.value) && Integer.parseInt(EndValue)>=Integer.parseInt(e.value)) {
  	  				enums.add(e);	
  	  			}
  			}else if(StringUtils.isNotBlank(StartValue)){
  				if (Integer.parseInt(StartValue)<=Integer.parseInt(e.value)){
  					enums.add(e);	
  				}
  			}else if(StringUtils.isNotBlank(EndValue)){
  				if (Integer.parseInt(EndValue)>=Integer.parseInt(e.value)){
  					enums.add(e);	
  				}
  			}
  		}
  		return enums;
  	}
  	
  	public static List<ProjStatusEnum> getThanValueNew(String StartValue,String EndValue) {
  		List<ProjStatusEnum> enums=new ArrayList<ProjStatusEnum>();
  		for (ProjStatusEnum e : ProjStatusEnum.values()) {
  			if(StringUtils.isNotBlank(StartValue) && StringUtils.isNotBlank(EndValue)){
  				if (Integer.parseInt(StartValue)<=Integer.parseInt(e.value.substring(0,4)) && Integer.parseInt(EndValue)>=Integer.parseInt(e.value.substring(0,4))) {
  	  				enums.add(e);	
  	  			}
  			}else if(StringUtils.isNotBlank(StartValue)){
  				if (Integer.parseInt(StartValue)<=Integer.parseInt(e.value.substring(0,4))){
  					enums.add(e);	
  				}
  			}else if(StringUtils.isNotBlank(EndValue)){
  				if (Integer.parseInt(EndValue)>=Integer.parseInt(e.value.substring(0,4))){
  					enums.add(e);	
  				}
  			}
  		}
  		return enums;
  	}
}
