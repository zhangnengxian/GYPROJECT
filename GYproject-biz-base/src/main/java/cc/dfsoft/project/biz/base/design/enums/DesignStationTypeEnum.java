package cc.dfsoft.project.biz.base.design.enums;

public enum DesignStationTypeEnum {
	GAS_STATION("110701","天然气所"),
	STORAGE_STATION("110702","场站所"),
	COMPREHENSIVE_STATION("110703","综合所");
	private final String value;
    
    private final String message;
    

    DesignStationTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }
    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static DesignStationTypeEnum valueof(String value) {
    	for(DesignStationTypeEnum e: DesignStationTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
}
