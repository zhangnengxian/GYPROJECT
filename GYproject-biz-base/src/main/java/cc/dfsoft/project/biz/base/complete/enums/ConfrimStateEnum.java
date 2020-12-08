package cc.dfsoft.project.biz.base.complete.enums;

public enum ConfrimStateEnum {
	
	FEEDBACK("1","已反馈"),
	TO_FEEDBACK("0","待反馈");
	
	private final String value;
    
    private final String message;
    
    ConfrimStateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ConfrimStateEnum valueof(String value) {
    	for(ConfrimStateEnum e: ConfrimStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
