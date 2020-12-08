package cc.dfsoft.project.biz.base.subpackage.enums;

public enum SubBudgetIsPassEnum {
	
	ALREADY_PASS("1","已通过"),
	HAVE_NOT_PASS("0","未通过");
	
	private final String value;
	private final String message;
	
	SubBudgetIsPassEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static SubBudgetIsPassEnum valueof(String value) {
    	for(SubBudgetIsPassEnum e: SubBudgetIsPassEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
