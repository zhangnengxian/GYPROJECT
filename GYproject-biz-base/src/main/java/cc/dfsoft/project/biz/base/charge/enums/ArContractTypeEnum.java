package cc.dfsoft.project.biz.base.charge.enums;

public enum ArContractTypeEnum {

    INTELLIGENCE("1","智能表合同"),
    SUP_CONTRACT("2","补充协议"),
    SUP_INTELLIGENCE("3","智能表补充协议"),
    CONSTRUCTION("0","施工合同");

	private final String value;

    private final String message;

    ArContractTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ArContractTypeEnum valueof(String value) {
    	for(ArContractTypeEnum e: ArContractTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
