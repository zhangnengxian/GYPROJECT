package cc.dfsoft.project.biz.base.contract.enums;

public enum PayTypeEnum {
	
	FULLY_PAID_UP("1","合同签订五日内一次付清"),
	INSTALLMENT("2","80%首付款"),
	OTHER("3","其它");
	
	private final String value;
    
    private final String message;
    
    PayTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PayTypeEnum valueof(String value) {
    	for(PayTypeEnum e: PayTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	

}
