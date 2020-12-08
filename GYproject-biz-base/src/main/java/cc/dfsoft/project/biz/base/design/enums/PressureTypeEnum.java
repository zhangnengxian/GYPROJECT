package cc.dfsoft.project.biz.base.design.enums;


public enum PressureTypeEnum {

	DEPRESSION("0","低压","11020301"),
	MEDIUM_PRESSURE("1","中压","11020302"),
	HIGH_PRESSURE("2","次高压","11020303");
	
	private final String value;
    
    private final String message;
    
    private final String type;
    
    PressureTypeEnum(String value, String message,String type){
        this.value = value;
        this.message = message;
        this.type = type;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PressureTypeEnum valueof(String value) {
    	for(PressureTypeEnum e: PressureTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    public String getType() {
		return type;
	}
}