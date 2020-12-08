package cc.dfsoft.project.biz.base.budget.enums;

public enum GovAuditCostTypeEnum {
	BUDGET("1","预算审定价"),
	SETTLEMENT("2","结算审定价");

	
	private final String value;
    
    private final String message;
    
    GovAuditCostTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static GovAuditCostTypeEnum valueof(String value) {
    	for(GovAuditCostTypeEnum e: GovAuditCostTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
