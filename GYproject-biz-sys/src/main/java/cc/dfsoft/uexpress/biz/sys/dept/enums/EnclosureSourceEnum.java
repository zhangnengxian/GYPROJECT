package cc.dfsoft.uexpress.biz.sys.dept.enums;

public enum EnclosureSourceEnum {
	
	STAFF("1","员工管理"),
	UPDATE("2","更新信息");
	private final String value;
    
    private final String message;
    
    EnclosureSourceEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static EnclosureSourceEnum valueof(String value) {
    	for(EnclosureSourceEnum e: EnclosureSourceEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
