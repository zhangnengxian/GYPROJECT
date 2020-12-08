package cc.dfsoft.project.biz.base.inspection.enums;

public enum ProcessStepEnum {
	
	PIPE_DERUSTING("1","钢管除锈"),
	PIPE_PRESERVATIVE("2","钢管防腐");
	
	private final String value;
    
    private final String message;
    
    ProcessStepEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProcessStepEnum valueof(String value) {
    	for(ProcessStepEnum e: ProcessStepEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
