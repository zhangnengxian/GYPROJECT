package cc.dfsoft.project.biz.base.subpackage.enums;

public enum PayTypeEnum {

	CONSTRUCTION("1","施工款"),
    SUPERVISOR("2","监理费"),
    DETECTION("3","检测费");

	private final String value;

    private final String message;

    PayTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public static PayTypeEnum valueof(String value) {
    	for(PayTypeEnum e: PayTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
