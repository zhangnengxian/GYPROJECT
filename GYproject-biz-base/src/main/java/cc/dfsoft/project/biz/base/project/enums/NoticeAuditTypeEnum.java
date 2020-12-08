package cc.dfsoft.project.biz.base.project.enums;

public enum NoticeAuditTypeEnum {
	
	SURVEY_AUDIT("01","踏勘审核"),
	DRAWING_AUDIT("02","图纸审核"),
	BUDGET_AUDIT("03","预算审核"),
	COST_AUDIT("04","造价审核"),
	CONTRACT_AUDIT("05","合同审核"),
	PLAN_AUDIT("06","计划审核"),
	CON_BUDGET_FIRST_AUDIT("0601","施工预算初审"),
	CON_BUDGET_AUDIT("07","施工预算审核"),
	DATA_AUDIT("08","资料审核"),
	SETTLEMENT_FIRST_AUDIT("0801","结算初审"),
	SETTLEMENT_AUDIT("09","结算审核"),
	TURN_FIXED_AUDIT("10","转固审核"),
	DELAT_AUDIT("11","延期审核"),
	PAY_AUDIT("12","付款审核"),
	/*TOUCH_PLAN_AUDIT("13","碰口审核"),*/
	CONTRACT_MODIFY_AUDIT("13","合同修改审核"),
	DIVISIONAL_ACCEPTANCE_AUDIT("14","分部验收审核"),
	JOINT_ACCEPTANCE_AUDIT("141","联合验收审核"),
	GAS_AUDIT("15","通气审核"),
	CHANGE_AUDIT("16","变更审核"),
	ENGINEERING_AUDIT("17","签证审核"),
	BACK_AUDIT("18","回退审核"),
	SUPPLEMENTAL_CONTRACT_AUDIT("19","协议审核"),
	SUB_CONTRACT_AUDIT("20","分合同修改审核"),
	SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT("21","协议修改审核"),
	SUB_CONTRACT__AUDIT("22","施工合同审核"),
	PROJECT_ACCEPT_AUDIT("0101","受理申请审核");
	private final String value;
    
    private final String message;
    
    NoticeAuditTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static NoticeAuditTypeEnum valueof(String value) {
    	for(NoticeAuditTypeEnum e: NoticeAuditTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    public static String[] getMessages(){
    	String[] messages = new String[AreaEnum.values().length];
    	for(int i=0;i<NoticeAuditTypeEnum.values().length;i++){
    		messages[i]=NoticeAuditTypeEnum.values()[i].getMessage();
    	}
    	return messages;
    }
}
