package cc.dfsoft.project.biz.base.inspection.enums;

public enum InspectionClumEnum {
	
	QUALIFIED("1","合格"),
	RE_DECLARE("2","纠错重报");
	
	private final String value;
    
    private final String message;
    
    InspectionClumEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static InspectionClumEnum valueof(String value) {
    	if(null!=value){
    	for(InspectionClumEnum e: InspectionClumEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}}
    	return null;
    }	
}
