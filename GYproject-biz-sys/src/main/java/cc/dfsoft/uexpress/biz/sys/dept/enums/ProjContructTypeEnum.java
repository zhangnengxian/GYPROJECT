package cc.dfsoft.uexpress.biz.sys.dept.enums;

public enum ProjContructTypeEnum {
	
	LARGE_SCALE("1","大规模"),
	SMALL_SCALE("2","小规模"),
	STATION_PROJ("3","场站工程");
	
	private final String value;
    
    private final String message;
    
    ProjContructTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjContructTypeEnum valueof(String value) {
    	for(ProjContructTypeEnum e: ProjContructTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	
}
