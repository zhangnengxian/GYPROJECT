package cc.dfsoft.project.biz.base.complete.enums;

public enum AcceptanceTypeEnum {
	JOINT_ACCEPTANCE("1","联合验收"),
	ONE_STOP_ACCEPTANCE("2","一站式验收");
	
	private final String value;
    
    private final String message;
    
    AcceptanceTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AcceptanceTypeEnum valueof(String value) {
    	for(AcceptanceTypeEnum e: AcceptanceTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
