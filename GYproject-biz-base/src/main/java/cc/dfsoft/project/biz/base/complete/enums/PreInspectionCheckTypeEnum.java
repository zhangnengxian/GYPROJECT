package cc.dfsoft.project.biz.base.complete.enums;

public enum PreInspectionCheckTypeEnum {

	COURTYARD("1","庭院"),
	INDOOR_PROJECT("2","户内"),
	PRESSURE_BOX("3","调压设施"),
	METERING_DEVICE("4","计量设备");
	
	private final String value;
    
    private final String message;
    
    PreInspectionCheckTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PreInspectionCheckTypeEnum valueof(String value) {
    	for(PreInspectionCheckTypeEnum e: PreInspectionCheckTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
