package cc.dfsoft.project.biz.base.constructmanage.enums;

import cc.dfsoft.project.biz.base.project.enums.StepEnum;

public enum StepOutWorkflowEnum {
	
	GAS_PROJECT_AUDIT("18041-1","带气审核",5),
    STAGE_PROJECT_AUDIT("1203-1","分期申请审核",5),
	ENGINEERING_AUDIT("1701-1","签证审核",5),
	ENGINEERING_QUANTY_AUDIT("1701-2","签证量审核",5),
	DIVISIONAL_ACCEPTANCE_AUDIT("4001","分部验收审核",5),
	PAYMENT_AUDIT("4002","付款审核",5),
	AUDIT_COMPLETE_DRAWING("4003","电子图审核",5),
	AUDIT_DELAY_APPLY("4004","延期审核",5),
	CONTRACT_MODIFY("1402-1","合同修改",5),
	CONTRACT_MODIFY_AUDIT("1403-1","合同修改审核",5),
	INTELLIGENT_CONTRACT("1402-3","智能表合同",5),
	INTELLIGENT_SUB_CONTRACT("1604-1","智能表分包合同",5),
	INTELLIGENT_MODIFY_AUDIT("1403-3","智能表合同修改审核",5),
	INTELLIGENT_SUPPLEMENT("1403-4","智能表补充协议",5),
	INTELLIGENT_SUPPLEMENT_AUDIT("1403-5","智能表补充协议审核",5),
	FALLBACK_AUDIT("4005","回退审核",5),
	SUPPLEMENTAL_CONTRACT_AUDIT("4006","补充协议审核",5),
	SC_MODIFY("1604-1","施工合同修改",5),
	SC_MODIFY_AUDIT("1604-2","施工合同修改审核",5),
	SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT("4007","补充协议修改审核",5),
	DESIGN_CHANGE_AUDIT("4008","变更审核",5),
	MAIN_PROGRESS("1000","主合同修改",5),
	ENGINEERING_RECORD("120206","签证记录",5),
	AUDIT_SETTLEMENT_JONINTLY_SIGN("110819","结算汇签审核",5);

	private final String value;
    
    private final String message;
    
    private final int stepDay;

    StepOutWorkflowEnum(String value, String message,int stepDay){
        this.value = value;
        this.message = message;
        this.stepDay=stepDay;
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
    public static StepOutWorkflowEnum valueof(String value) {
    	for(StepOutWorkflowEnum e: StepOutWorkflowEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    public static String[] getMessages(){
    	String[] messages = new String[StepOutWorkflowEnum.values().length];
    	for(int i=0;i<StepOutWorkflowEnum.values().length;i++){
    		messages[i]=StepOutWorkflowEnum.values()[i].getMessage();
    	}
    	return messages;
    }


	public static String getNameByCode(String code){
		for(StepOutWorkflowEnum e: StepOutWorkflowEnum.values()) {
			if(e.getValue().equals(code)) {
				return e.getMessage();
			}
		}
		return null;
	}
}
