package cc.dfsoft.project.biz.base.project.enums;

public enum IsGasEnum {

	IS_GAS("1","已带气"),
	NOT_GAS("0","未带气");

	private final String value;

    private final String message;

    IsGasEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static IsGasEnum valueof(String value) {
    	for(IsGasEnum e: IsGasEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	

}
