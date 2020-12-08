package cc.dfsoft.project.biz.base.baseinfo.enums;

public enum BusinessPartnersTypeEnum {
	BUILD("5", "分包单位");
	
	private final String value;
    
    private final String message;
    
    BusinessPartnersTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static BusinessPartnersTypeEnum valueof(String value) {
    	for(BusinessPartnersTypeEnum e: BusinessPartnersTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
