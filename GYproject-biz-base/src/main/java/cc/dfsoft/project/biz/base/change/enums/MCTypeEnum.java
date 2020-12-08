package cc.dfsoft.project.biz.base.change.enums;

public enum MCTypeEnum {
	MATERIAL_CHANGE("0","材料变更"),
	MATERIAL_VISA("1","工程签证");
	
	private final String value;
    
    private final String message;
    
    MCTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static MCTypeEnum valueof(String value) {
    	for(MCTypeEnum e: MCTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
