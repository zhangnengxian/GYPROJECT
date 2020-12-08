package cc.dfsoft.project.biz.base.contract.enums;

public enum ModifyStatusEnum {
	NO_MODIFY("3","无修改"),
	TO_AUDIT("0","待审核"),
	AUDIT_PASS("1","已通过"),
	AUDIT_NO_PASS("2","未通过");
	private final String value;
    
    private final String message;
    
    ModifyStatusEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ModifyStatusEnum valueof(String value) {
    	for(ModifyStatusEnum e: ModifyStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
	
	
}
