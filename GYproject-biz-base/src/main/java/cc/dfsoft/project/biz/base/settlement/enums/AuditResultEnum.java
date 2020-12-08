package cc.dfsoft.project.biz.base.settlement.enums;

public enum AuditResultEnum {
	
	NOT_APPLY("0","未推送"),
	APPLYING("1","审核中"),
	NOT_PASSED("2","未通过"),
	PASSED("3","通过"),
	DISPATCH("4","待派工"),
	TO_CANCEL("-1","作废");
	
	
	private final String value;
    
    private final String message;
    
    AuditResultEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AuditResultEnum valueof(String value) {
    	for(AuditResultEnum e: AuditResultEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
