package cc.dfsoft.project.biz.base.project.enums;

public enum MrResultEnum {
	
	PASSED("1","通过"),
	NOT_PASSED("0","不通过");
	
	private final String value;
    
    private final String message;
    
    MrResultEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static MrResultEnum valueof(String value) {
    	for(MrResultEnum e: MrResultEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
