package cc.dfsoft.project.biz.base.contract.enums;

public enum ContractMethodEnum {
	
	SUPPLY("1","甲供工程"),
	CLEAN("2","清包工"),
	LUMP("3","包干"),
	OTHER("4","其他方式");
	
	private final String value;
    
    private final String message;
    
    ContractMethodEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ContractMethodEnum valueof(String value) {
    	for(ContractMethodEnum e: ContractMethodEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	

}
