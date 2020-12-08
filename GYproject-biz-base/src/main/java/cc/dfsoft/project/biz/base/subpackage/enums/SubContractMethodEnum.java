package cc.dfsoft.project.biz.base.subpackage.enums;

public enum SubContractMethodEnum {
	
	SUPPLY("1","甲供工程"),
	CLEAN("2","清包工"),
	LUMP("3","包工包料"),
	OTHER("4","其他");
	
	private final String value;
    
    private final String message;
    
    SubContractMethodEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SubContractMethodEnum valueof(String value) {
    	for(SubContractMethodEnum e: SubContractMethodEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	

}
