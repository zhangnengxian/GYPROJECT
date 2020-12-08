package cc.dfsoft.project.biz.base.project.enums;

public enum IsSignEnum {

	IS_SIGN("1","是"),
	NOT_SIGN("0","否");

	private final String value;

    private final String message;

    IsSignEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static IsSignEnum valueof(String value) {
    	for(IsSignEnum e: IsSignEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
