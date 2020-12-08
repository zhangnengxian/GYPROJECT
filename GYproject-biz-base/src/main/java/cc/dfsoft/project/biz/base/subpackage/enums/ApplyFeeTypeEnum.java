package cc.dfsoft.project.biz.base.subpackage.enums;

public enum ApplyFeeTypeEnum {
	CONSTRUCTION_SFEE("1","工程费"),
	THIRD_PARTY_FEE("2","设计费");
	
	private final String value;
    
    private final String message;
    
    ApplyFeeTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public static ApplyFeeTypeEnum valueof(String value) {
    	for(ApplyFeeTypeEnum e: ApplyFeeTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
