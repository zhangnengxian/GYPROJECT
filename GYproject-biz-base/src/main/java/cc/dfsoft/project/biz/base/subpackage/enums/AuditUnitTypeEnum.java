package cc.dfsoft.project.biz.base.subpackage.enums;


public enum AuditUnitTypeEnum {
	MARKET_DEVELOPMENT_DEPARTMENT("1","市场发展部"),
	CUSTOMER_SERVICE_CENTER("0","客服中心");
	
	private final String value;
    
    private final String message;
    
    AuditUnitTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AuditUnitTypeEnum valueof(String value) {
    	for(AuditUnitTypeEnum e: AuditUnitTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
