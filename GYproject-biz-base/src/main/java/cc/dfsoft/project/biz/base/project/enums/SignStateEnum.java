package cc.dfsoft.project.biz.base.project.enums;

public enum SignStateEnum {


	NO_SIGN("0","未签字"),
	READY_SIGN("1","准备签字"),
	ALREADY_SIGN("2","已签字");
	
	private final String value;
    
    private final String message;
    
    SignStateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SignStateEnum valueof(String value) {
    	for(SignStateEnum e: SignStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
