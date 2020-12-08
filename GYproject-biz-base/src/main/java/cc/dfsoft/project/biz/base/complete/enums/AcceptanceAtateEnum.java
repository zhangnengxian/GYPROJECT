package cc.dfsoft.project.biz.base.complete.enums;

public enum AcceptanceAtateEnum {
	ALREADY_ACCEPTANCE("1","已验收"),
	NOT_ACCEPTANCE("0","未验收");
	
	private final String value;
    
    private final String message;
    
    AcceptanceAtateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AcceptanceAtateEnum valueof(String value) {
    	for(AcceptanceAtateEnum e: AcceptanceAtateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
