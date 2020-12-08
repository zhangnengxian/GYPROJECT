package cc.dfsoft.project.biz.base.complete.enums;

public enum CheckTypeEnum {
	
	MAIN_PIPE_PROJECT("1","户内管"),
	INDOOR_PROJECT("2","中低压管、庭院管");
	
	private final String value;
    
    private final String message;
    
    CheckTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static CheckTypeEnum valueof(String value) {
    	for(CheckTypeEnum e: CheckTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
