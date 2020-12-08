package cc.dfsoft.project.biz.base.charge.enums;

public enum BillStateEnum {
	
	NOBill("0","未开"),
	YESBill("1","已开");

	private final String value;
    
    private final String message;
    
    BillStateEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BillStateEnum valueof(String value) {
    	for(BillStateEnum e: BillStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
