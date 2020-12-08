package cc.dfsoft.project.biz.base.contract.enums;

public enum ContractIsPrintEnum {
	
	ALREADY_PRINT("0","已打印"),
	HAVE_NOT_PRINT("1","未打印");
	
	private final String value;
	private final String message;
	
	ContractIsPrintEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static ContractIsPrintEnum valueof(String value) {
    	for(ContractIsPrintEnum e: ContractIsPrintEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
