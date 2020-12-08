package cc.dfsoft.project.biz.base.charge.enums;

public enum ARStatusEnum {
	
	TO_BE_CHARGE("1","待收款"),
	ALREADY_CHARGE("2","已收款");
	

	private final String value;
    
    private final String message;
    
    ARStatusEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ARStatusEnum valueof(String value) {
    	for(ARStatusEnum e: ARStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
