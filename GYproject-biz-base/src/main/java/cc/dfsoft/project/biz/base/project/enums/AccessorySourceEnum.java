package cc.dfsoft.project.biz.base.project.enums;

public enum AccessorySourceEnum {
	
	COLLECT_FILE("0","采集"),
	PHOTO_FILE("1","拍照"),
	CHANGE_FILE("2","变更");
	private final String value;
    
    private final String message;
    
    AccessorySourceEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AccessorySourceEnum valueof(String value) {
    	for(AccessorySourceEnum e: AccessorySourceEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
