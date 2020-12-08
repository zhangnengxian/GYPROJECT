package cc.dfsoft.project.biz.base.contract.enums;

public enum ContractSuIsPrintEnum {
	
	ALREADY_PRINT("0","已打印"),
	HAVE_NOT_PRINT("1","未打印");
	
	private final String value;
	private final String message;
	
	ContractSuIsPrintEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static ContractSuIsPrintEnum valueof(String value) {
    	for(ContractSuIsPrintEnum e: ContractSuIsPrintEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
