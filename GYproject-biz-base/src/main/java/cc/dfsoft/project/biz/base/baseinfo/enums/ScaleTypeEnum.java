package cc.dfsoft.project.biz.base.baseinfo.enums;

public enum ScaleTypeEnum {

	LARGE_SCALE("1", "大规模"),
	SMALL_SCALE("2", "小规模");
	
	private final String value;
    
    private final String message;
    
    ScaleTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ScaleTypeEnum valueof(String value) {
    	for(ScaleTypeEnum e: ScaleTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
