package cc.dfsoft.project.biz.base.design.enums;

public enum DesignChangeStateEnum {
	TO_CANCEL("-1","废弃"),
	WAIT_PUSH("1","待推送"),
	CHNAGE_AUDTI("8","待变更审核"),
	WAIT_CONFIRM("2","确认中"),
	WAIT_BUDGET_CHANGE("3","待预算调整"),
	WAIT_SUPPLEMENT_CONTRACT("4","待签补充协议"),
	ALREADY_SIGN_SUPPLEMENT("5","已签补充协议"),
	WAIT_AUDIT("6","待补充协议审核"),
	ALREADY_FINISHED("7","已完成");
	private final String value;
    
    private final String message;
    

    DesignChangeStateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }
    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static DesignChangeStateEnum valueof(String value) {
    	for(DesignChangeStateEnum e: DesignChangeStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
