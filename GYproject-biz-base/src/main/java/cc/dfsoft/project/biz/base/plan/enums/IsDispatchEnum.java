package cc.dfsoft.project.biz.base.plan.enums;

public enum IsDispatchEnum {

	NOT_DISPATCH("0","未派遣"),
	IS_DISPATCH("1","已派遣");

	private final String value;
	private final String message;

	IsDispatchEnum(String value, String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static IsDispatchEnum valueof(String value) {
    	for(IsDispatchEnum e: IsDispatchEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
