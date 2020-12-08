package cc.dfsoft.project.biz.base.charge.enums;

public enum BillTypeEnum {
	
	RECEIPT("0","收据"),
	INVOICE("1","发票");

	private final String value;
    
    private final String message;
    
    BillTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BillTypeEnum valueof(String value) {
    	for(BillTypeEnum e: BillTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
