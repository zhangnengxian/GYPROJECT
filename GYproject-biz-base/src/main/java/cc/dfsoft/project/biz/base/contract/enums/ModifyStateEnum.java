package cc.dfsoft.project.biz.base.contract.enums;

public enum ModifyStateEnum {
	BEFOR_MODIFY("1","修改前"),
	AFTER_MODIFY("2","修改后");
	private final String value;
    
    private final String message;
    
    ModifyStateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ModifyStateEnum valueof(String value) {
    	for(ModifyStateEnum e: ModifyStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
	
	
}
