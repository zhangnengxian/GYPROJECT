package cc.dfsoft.project.biz.base.complete.enums;

public enum CheckResultEnum {
	
	GOOD("1","是"),
	BAD("0","否"),
    NULL("2","无");
	
	private final String value;
    
    private final String message;
    
    CheckResultEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static CheckResultEnum valueof(String value) {
    	for(CheckResultEnum e: CheckResultEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
