package cc.dfsoft.project.biz.base.charge.enums;

public enum ARPayStatusEnum {
	
	TO_BE_PAY("1","未付款"),
	TO_APPLY_PAY("2","已登记请款"),
	ALREADY_PAY("3","已付款");

	private final String value;
    
    private final String message;
    
    ARPayStatusEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ARPayStatusEnum valueof(String value) {
    	for(ARPayStatusEnum e: ARPayStatusEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
