package cc.dfsoft.project.biz.base.complete.enums;

public enum PreQuantityCheckTypeEnum {
	
	COVER("1","封面"),
	COMPLETE_DRAWING("2","竣工图"),
	COMPLETE_DATA("3","竣工资料"),;
	
	private final String value;
    
    private final String message;
    
    PreQuantityCheckTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PreQuantityCheckTypeEnum valueof(String value) {
    	for(PreQuantityCheckTypeEnum e: PreQuantityCheckTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
