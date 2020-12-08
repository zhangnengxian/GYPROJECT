package cc.dfsoft.project.biz.base.contract.enums;

public enum MaterialIsPassRegisterEnum {
	
	ALREADY_REGISTER("1","已登记"),
	HAVE_NOT_REGISTER("0","未登记");
	
	private final String value;
	private final String message;
	
	MaterialIsPassRegisterEnum(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static MaterialIsPassRegisterEnum valueof(String value) {
    	for(MaterialIsPassRegisterEnum e: MaterialIsPassRegisterEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
