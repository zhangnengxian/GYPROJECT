package cc.dfsoft.project.biz.base.update.enums;

public enum UpdateInfoTypeEnum {

	PRICED_BOQ("01","工程量标准");

	
	private final String value;
    
    private final String message;
    
    UpdateInfoTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static UpdateInfoTypeEnum valueof(String value) {
    	for(UpdateInfoTypeEnum e: UpdateInfoTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
}