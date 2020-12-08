package cc.dfsoft.project.biz.base.contract.enums;

public enum ContractIsPassEnum {
	
	ALREADY_PASS("1","已通过"),
	HAVE_NOT_PASS("0","未通过");
	
	private final String value;
	private final String message;
	
	ContractIsPassEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static ContractIsPassEnum valueof(String value) {
    	for(ContractIsPassEnum e: ContractIsPassEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
