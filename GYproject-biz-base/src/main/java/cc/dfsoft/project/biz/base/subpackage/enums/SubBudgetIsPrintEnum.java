package cc.dfsoft.project.biz.base.subpackage.enums;

public enum SubBudgetIsPrintEnum {
	
	ALREADY_PRINT("0","已打印"),
	HAVE_NOT_PRINT("1","未打印");
	
	private final String value;
	private final String message;
	
	SubBudgetIsPrintEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static SubBudgetIsPrintEnum valueof(String value) {
    	for(SubBudgetIsPrintEnum e: SubBudgetIsPrintEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
