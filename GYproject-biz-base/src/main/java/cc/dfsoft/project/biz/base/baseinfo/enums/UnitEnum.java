package cc.dfsoft.project.biz.base.baseinfo.enums;

public enum UnitEnum {
	METER("01", "m"),
	STERE("02", "m³"),
	INDIVIDUAL("03", "个"),
	BAR("04", "条"),
	SEAT("05", "座"),
	SUIT("06", "套");
	
    private final String value;
    
    private final String message;
    
    UnitEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static UnitEnum valueof(String value) {
    	for(UnitEnum e: UnitEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
