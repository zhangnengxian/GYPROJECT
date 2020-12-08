package cc.dfsoft.project.biz.base.change.enums;

public enum ChangeStateEnum {
	
	 NO_HANDLE("0","未处理"),
     HANDLED("1","已处理"),
     INVALID("-1","作废");
	
private final String value;
    
    private final String message;
    
    ChangeStateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ChangeStateEnum valueof(String value) {
    	for(ChangeStateEnum e: ChangeStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
