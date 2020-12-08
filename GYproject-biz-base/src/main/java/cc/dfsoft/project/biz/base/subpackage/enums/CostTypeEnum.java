package cc.dfsoft.project.biz.base.subpackage.enums;

public enum CostTypeEnum {
	
	DETAILED_LIST("1","清单计价"),
	QUOTA("2","定额计价");
	
	private final String value;
    
    private final String message;
    
    CostTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public static CostTypeEnum valueof(String value) {
    	for(CostTypeEnum e: CostTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
