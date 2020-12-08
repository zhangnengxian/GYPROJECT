package cc.dfsoft.project.biz.base.charge.enums;

public enum ARFlagEnum {
	
	RECEIVE_ACCOUNT("1","收款"),
	ACCOUNTS_PAY("-1","付款"),
	REFUND_MENT("0","退款");

	private final String value;
    
    private final String message;
    
    ARFlagEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ARFlagEnum valueof(String value) {
    	for(ARFlagEnum e: ARFlagEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
