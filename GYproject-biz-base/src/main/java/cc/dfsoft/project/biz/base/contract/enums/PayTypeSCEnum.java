
package cc.dfsoft.project.biz.base.contract.enums;

public enum PayTypeSCEnum {
	INSTALLMENT("2","50%"),
	FULLY_PAID_UP("1","30%");
	
	
	private final String value;
    
    private final String message;
    
    PayTypeSCEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static PayTypeSCEnum valueof(String value) {
    	for(PayTypeSCEnum e: PayTypeSCEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	

}
