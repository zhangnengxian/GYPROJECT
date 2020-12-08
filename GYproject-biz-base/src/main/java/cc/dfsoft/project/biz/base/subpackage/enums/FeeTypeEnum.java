package cc.dfsoft.project.biz.base.subpackage.enums;

public enum FeeTypeEnum {

	CONSFEE("1","工程费"),
	DESIGN_FEE("2","设计费"),
	SU_FEE("3","监理费"),
	CHECK_FEE("4","探伤费");
	
	private final String value;
    
    private final String message;
    
    FeeTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public static FeeTypeEnum valueof(String value) {
    	for(FeeTypeEnum e: FeeTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
