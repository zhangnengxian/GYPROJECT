package cc.dfsoft.project.biz.base.project.enums;

public enum SignGenarateTypeEnum {
	
	SINGLE("1","单个"),
	MULTIPLE("2","多个");

	private final String value;
    
    private final String message;
    
    SignGenarateTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SignGenarateTypeEnum valueof(String value) {
    	for(SignGenarateTypeEnum e: SignGenarateTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
