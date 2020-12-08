package cc.dfsoft.project.biz.base.common.enums;

public enum VersionTypeEnum {

	PRICED_BOQ("01","工程量标准");

	
	private final String value;
    
    private final String message;
    
    VersionTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static VersionTypeEnum valueof(String value) {
    	for(VersionTypeEnum e: VersionTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
}